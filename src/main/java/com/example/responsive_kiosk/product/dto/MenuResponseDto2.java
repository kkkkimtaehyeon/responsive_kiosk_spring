package com.example.responsive_kiosk.product.dto;

import com.example.responsive_kiosk.product.entity.Menu;
import lombok.Builder;
import lombok.Data;

@Data
public class MenuResponseDto2 {
    Long id;
    String imagePath;
    String name;
    Double price;
    String description;

    @Builder
    public MenuResponseDto2(Menu menu) {
        this.id = menu.getId();
        this.imagePath = menu.getImagePath();
        this.name = menu.getName();
        this.price = menu.getPrice();
        this.description = menu.getDescription();
    }
}
