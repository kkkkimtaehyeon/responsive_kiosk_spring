package com.example.responsive_kiosk.product.controller;

import com.example.responsive_kiosk.product.dto.CategorySaveRequestDto;
import com.example.responsive_kiosk.product.entity.Category;
import com.example.responsive_kiosk.product.service.CategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CategoryApiController {

    private final CategoryService categoryService;
    @PostMapping("/api/categories")
    public ResponseEntity<Long> saveCategory(CategorySaveRequestDto requestDto) {
        return categoryService.save(requestDto);
    }

    @GetMapping("/api/categories")
    public List<Category> getAllCategories() {
        return categoryService.getAll();
    }

    @PutMapping("/api/categories/{id}")
    public void updateCategory(@PathVariable("id") Long id, @RequestParam String name) {
        categoryService.update(id, name);
    }


}
