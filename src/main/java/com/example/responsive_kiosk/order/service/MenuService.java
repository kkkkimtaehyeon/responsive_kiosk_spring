package com.example.responsive_kiosk.order.service;

import com.example.responsive_kiosk.order.dto.MenuSaveRequestDto;
import com.example.responsive_kiosk.order.entity.Category;
import com.example.responsive_kiosk.order.repository.CategoryRepository;
import com.example.responsive_kiosk.order.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class MenuService {
    private final MenuRepository menuRepository;
    private final CategoryRepository categoryRepository;

    public ResponseEntity<Long> save(MenuSaveRequestDto requestDto) {
        Category category = categoryRepository.findById(requestDto.getCategoryId()).orElseThrow(() -> new IllegalArgumentException("category not found"));
        requestDto.setCategory(category);
        return ResponseEntity.ok(menuRepository.save(requestDto.toEntity()).getId());
    }

}
