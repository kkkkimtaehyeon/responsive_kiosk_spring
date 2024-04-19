package com.example.responsive_kiosk.order.dto;

import com.example.responsive_kiosk.order.entity.Category;
import com.example.responsive_kiosk.order.entity.Menu;
import lombok.Builder;
import lombok.Data;

@Data
public class MenuResponseDto {

    Long id;
    String image;
    String name;
    String price;
    String category;

    @Builder
    public MenuResponseDto(Menu menu) {
        this.id = menu.getId();
        this.image = menu.getImage();
        this.name = menu.getName();
        this.price = menu.getPrice();
        this.category = menu.getCategory().getName();
    }

}
