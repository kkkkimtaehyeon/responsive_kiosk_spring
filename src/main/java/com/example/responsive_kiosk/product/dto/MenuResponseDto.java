package com.example.responsive_kiosk.product.dto;

import com.example.responsive_kiosk.product.entity.Menu;
import lombok.Builder;
import lombok.Data;

@Data
public class MenuResponseDto {

    Long id;
    String imagePath;
    String name;
    Double price;
    String description;
    String categoryName;

    @Builder
    public MenuResponseDto(Menu menu) {
        this.id = menu.getId();
        this.imagePath = menu.getImagePath();
        this.name = menu.getName();
        this.price = menu.getPrice();
        this.description = menu.getDescription();
        this.categoryName = menu.getCategory().getName();
    }

}
