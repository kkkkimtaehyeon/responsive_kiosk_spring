package com.example.responsive_kiosk.order.dto;

import com.example.responsive_kiosk.order.entity.Category;
import com.example.responsive_kiosk.order.entity.Menu;
import lombok.Builder;
import lombok.Data;

@Data
public class MenuSaveRequestDto {

    String image;
    String name;
    String price;
    Long categoryId;
    Category category;

    @Builder
    public MenuSaveRequestDto(String image, String name, String price, Long categoryId) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
    }

    public Menu toEntity() {
        return Menu.builder()
                .image(image)
                .name(name)
                .price(price)
                .category(category)
                .build();

    }
}
