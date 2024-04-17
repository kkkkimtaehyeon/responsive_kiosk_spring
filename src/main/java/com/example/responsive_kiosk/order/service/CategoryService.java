package com.example.responsive_kiosk.order.service;

import com.example.responsive_kiosk.order.dto.CategorySaveRequestDto;
import com.example.responsive_kiosk.order.entity.Category;
import com.example.responsive_kiosk.order.repository.CategoryRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public ResponseEntity<Long> save(CategorySaveRequestDto requestDto) {
        return ResponseEntity.ok(categoryRepository.save(requestDto.toEntity()).getId());
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Transactional
    public void update(Long id, String name) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("category not found"));
        category.update(name);
    }
}
