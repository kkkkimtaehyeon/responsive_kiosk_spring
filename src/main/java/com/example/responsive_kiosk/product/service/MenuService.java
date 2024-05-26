package com.example.responsive_kiosk.product.service;

import com.example.responsive_kiosk.config.service.S3UploadService;
import com.example.responsive_kiosk.product.dto.MenuResponseDto;
import com.example.responsive_kiosk.product.dto.MenuResponseDto2;
import com.example.responsive_kiosk.product.dto.MenuSaveOnGPTRequestDto;
import com.example.responsive_kiosk.product.dto.MenuSaveRequestDto;
import com.example.responsive_kiosk.product.dto.MenuUpdateRequestDto;
import com.example.responsive_kiosk.product.entity.Category;
import com.example.responsive_kiosk.product.entity.Menu;
import com.example.responsive_kiosk.product.repository.CategoryRepository;
import com.example.responsive_kiosk.product.repository.MenuRepository;
import com.example.responsive_kiosk.toFastApi.ToFastApiService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@DynamicUpdate
@Service
public class MenuService {
    private final MenuRepository menuRepository;
    private final CategoryRepository categoryRepository;
    private final S3UploadService s3UploadService;
    private final ToFastApiService toFastApiService;

    @Transactional
    public ResponseEntity<Long> save(MenuSaveRequestDto requestDto) throws IOException {
        Category category = categoryRepository.findByName(requestDto.getCategoryName()).orElseThrow(EntityExistsException::new);
        String imagePath = s3UploadService.saveFile(requestDto.getImageFile());

        Menu menu = requestDto.toEntity(category, imagePath);
        Long savedMenuId;
        try {
            // 트랜잭션 시작
            savedMenuId = menuRepository.save(menu).getId();
            //fastapi 서버에 있는 GPT에 메뉴 학습을 위해 MenuSaveOnGPTRequestDto 전달
            toFastApiService.registerMenuOnGPT(new MenuSaveOnGPTRequestDto(menu));
        } catch (Exception e) {
            // 롤백
            savedMenuId = null;
            throw e; // 예외 다시 던지기
        }
        // 트랜잭션 종료 후 커밋
        return ResponseEntity.ok(savedMenuId);
    }

    public List<MenuResponseDto> getAll() {
        List<MenuResponseDto> responseDtoList = new ArrayList<>();
        List<Menu> menus = menuRepository.findAll(Sort.by(Direction.ASC, "Category"));
        for(Menu menu: menus) {
            responseDtoList.add(MenuResponseDto.builder().menu(menu).build());
        }
        return responseDtoList;
    }

    public List<MenuResponseDto> getAllByKeyword(String keyword) {
        List<MenuResponseDto> responseDtoList = new ArrayList<>();
        List<Menu> menus = menuRepository.findByNameContaining(keyword, Sort.by(Direction.ASC, "Category"));
        for(Menu menu: menus) {
            responseDtoList.add(MenuResponseDto.builder().menu(menu).build());
        }
        return responseDtoList;
    }

    public List<MenuResponseDto2> getMenusByCategory(String categoryName) {
        Category category = categoryRepository.findByName(categoryName).orElseThrow(EntityNotFoundException::new);
        List<Menu> menus = menuRepository.findByCategory(category);

        List<MenuResponseDto2> responseDtoList = new ArrayList<>();
        for(Menu menu: menus) {
            responseDtoList.add(MenuResponseDto2.builder().menu(menu).build());
        }
        return responseDtoList;
    }

    //MenuResponseDto2: 메뉴 전달할 때 필요한 정보들만 추려봄
    public List<MenuResponseDto2> getMenusByIds(List<Long> ids) {
        List<MenuResponseDto2> responseDtoList = new ArrayList<>();
        for(Long id: ids) {
            Menu menu = menuRepository.findById(id).orElseThrow(EntityNotFoundException::new);
            responseDtoList.add(MenuResponseDto2.builder().menu(menu).build());
        }
        return responseDtoList;
    }

    public ResponseEntity<MenuResponseDto> get(Long id) {
        Menu menu = menuRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("menu not found"));
        MenuResponseDto responseDto = MenuResponseDto.builder().menu(menu).build();
        return ResponseEntity.ok(responseDto);
    }

    @Transactional
    public ResponseEntity<Long> update(Long id, MenuUpdateRequestDto requestDto) {
        Menu menu = menuRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("menu not found"));
        //Category category = categoryRepository.findById(requestDto.getCategoryId()).orElseThrow(() -> new IllegalArgumentException("category not found"));
        //requestDto.setCategory(category);
        menu.update(requestDto);
        return ResponseEntity.ok(menu.getId());
    }

    @Transactional
    public ResponseEntity<String> delete(Long id) throws IOException {
        Menu menu = menuRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("menu not found"));
        try {
            menuRepository.delete(menu);
            ResponseEntity<String> response = toFastApiService.deleteMenuOnGPT(id);

            return response;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Failed to delete menu", e);

        }
        //return ResponseEntity.status(HttpStatus.SC_UNPROCESSABLE_ENTITY).body(String.valueOf(id));
    }

}
