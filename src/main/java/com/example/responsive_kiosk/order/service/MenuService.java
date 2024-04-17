package com.example.responsive_kiosk.order.service;

import com.example.responsive_kiosk.order.dto.MenuResponseDto;
import com.example.responsive_kiosk.order.dto.MenuSaveRequestDto;
import com.example.responsive_kiosk.order.dto.MenuUpdateRequestDto;
import com.example.responsive_kiosk.order.entity.Category;
import com.example.responsive_kiosk.order.entity.Menu;
import com.example.responsive_kiosk.order.repository.CategoryRepository;
import com.example.responsive_kiosk.order.repository.MenuRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@DynamicUpdate
@Service
public class MenuService {
    private final MenuRepository menuRepository;
    private final CategoryRepository categoryRepository;

    public ResponseEntity<Long> save(MenuSaveRequestDto requestDto) {
        Category category = categoryRepository.findById(requestDto.getCategoryId()).orElseThrow(() -> new IllegalArgumentException("category not found"));
        requestDto.setCategory(category);
        return ResponseEntity.ok(menuRepository.save(requestDto.toEntity()).getId());
    }

    public List<MenuResponseDto> getAll() {
        List<MenuResponseDto> responseDtoList = new ArrayList<>();
        List<Menu> menus = menuRepository.findAll();
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
        Category category = categoryRepository.findById(requestDto.getCategoryId()).orElseThrow(() -> new IllegalArgumentException("category not found"));
        requestDto.setCategory(category);
        menu.update(requestDto);
        return ResponseEntity.ok(menu.getId());
    }

    public void delete(Long id) {
        Menu menu = menuRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("menu not found"));
        menuRepository.delete(menu);
    }

}
