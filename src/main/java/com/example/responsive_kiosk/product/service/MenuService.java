package com.example.responsive_kiosk.product.service;

import com.example.responsive_kiosk.config.service.S3UploadService;
import com.example.responsive_kiosk.product.dto.MenuResponseDto;
import com.example.responsive_kiosk.product.dto.MenuSaveRequestDto;
import com.example.responsive_kiosk.product.dto.MenuUpdateRequestDto;
import com.example.responsive_kiosk.product.entity.Menu;
import com.example.responsive_kiosk.product.repository.CategoryRepository;
import com.example.responsive_kiosk.product.repository.MenuRepository;
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
    public ResponseEntity<Long> save(MenuSaveRequestDto requestDto) throws IOException {
        //requestDto.setImagePath(saveImage(requestDto.getImageFile()));

        try {
            String imagePath = s3UploadService.saveFile(requestDto.getImageFile());
            requestDto.setImagePath(imagePath);
        }catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok(menuRepository.save(requestDto.toEntity()).getId());
    }

    /*public String saveImage(MultipartFile file) throws IOException {

        if(!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                String uploadDir = "/images";
                String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                File uploadfile = new File(uploadDir, fileName);
                file.transferTo(uploadfile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        *//*try {
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path uploadFilePath = Paths.get(UPLOAD_PATH, fileName);
            Files.copy(file.getInputStream(), uploadFilePath);

            return FILE_PATH + fileName;
        } catch (IOException e) {
            e.printStackTrace();
            return "이미지 업로드에 실패했습니다.";
        }*//*
    }*/
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
