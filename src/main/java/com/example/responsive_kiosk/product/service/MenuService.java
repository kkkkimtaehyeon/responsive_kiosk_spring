package com.example.responsive_kiosk.product.service;

import com.example.responsive_kiosk.config.service.S3UploadService;
import com.example.responsive_kiosk.product.dto.MenuResponseDto;
import com.example.responsive_kiosk.product.dto.MenuSaveRequestDto;
import com.example.responsive_kiosk.product.dto.MenuUpdateRequestDto;
import com.example.responsive_kiosk.product.entity.Category;
import com.example.responsive_kiosk.product.entity.Menu;
import com.example.responsive_kiosk.product.repository.CategoryRepository;
import com.example.responsive_kiosk.product.repository.MenuRepository;
import com.example.responsive_kiosk.toFastApi.ToFastApiService;
import jakarta.persistence.EntityExistsException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@DynamicUpdate
@Service
public class MenuService {
    private final MenuRepository menuRepository;
    private final CategoryRepository categoryRepository;
    private final S3UploadService s3UploadService;
    private final ToFastApiService toFastApiService;

    public ResponseEntity<Long> save(MenuSaveRequestDto requestDto) throws IOException {
        Category category = categoryRepository.findByName(requestDto.getCategoryName()).orElseThrow(EntityExistsException::new);
        String imagePath = s3UploadService.saveFile(requestDto.getImageFile());

        Long savedMenuId = menuRepository.save(requestDto.toEntity(category, imagePath)).getId();
        toFastApiService.registerMenuOnGPT(requestDto);

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

    public void delete(Long id) {
        Menu menu = menuRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("menu not found"));
        menuRepository.delete(menu);
    }

}
