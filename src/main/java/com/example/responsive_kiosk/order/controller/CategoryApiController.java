package com.example.responsive_kiosk.order.controller;

import com.example.responsive_kiosk.order.dto.CategorySaveRequestDto;
import com.example.responsive_kiosk.order.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CategoryApiController {

    private final CategoryService categoryService;
    @PostMapping("/api/categories")
    public ResponseEntity<Long> saveCategory(CategorySaveRequestDto requestDto) {
        return categoryService.save(requestDto);
    }


}
