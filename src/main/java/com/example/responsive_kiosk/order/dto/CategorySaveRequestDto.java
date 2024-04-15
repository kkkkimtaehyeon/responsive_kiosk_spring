package com.example.responsive_kiosk.order.dto;

import com.example.responsive_kiosk.order.entity.Category;
import lombok.Data;

@Data
public class CategorySaveRequestDto {

    String name;

    public Category toEntity() {
        return Category.builder().name(name).build();
    }
}
