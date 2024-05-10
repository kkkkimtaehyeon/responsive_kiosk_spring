package com.example.responsive_kiosk.product.dto;

import com.example.responsive_kiosk.product.entity.Menu;
import lombok.Builder;
import lombok.Data;

@Data
public class MenuSaveOnGPTRequestDto {

    String name;
    Double price;
    String description;
    String categoryName;
    String imagePath;

    @Builder
    public MenuSaveOnGPTRequestDto(Menu menu) {
        this.name = menu.getName();
        this.price = menu.getPrice();
        this.description = menu.getDescription();
        this.categoryName = menu.getCategory().getName();
        this.imagePath = menu.getImagePath();
    }
}
