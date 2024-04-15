package com.example.responsive_kiosk.order.service;

import com.example.responsive_kiosk.order.dto.CategorySaveRequestDto;
import com.example.responsive_kiosk.order.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public ResponseEntity<Long> save(CategorySaveRequestDto requestDto) {
        return ResponseEntity.ok(categoryRepository.save(requestDto.toEntity()).getId());
    }
}
