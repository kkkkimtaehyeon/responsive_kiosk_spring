package com.example.responsive_kiosk.product.dto;

import com.example.responsive_kiosk.product.entity.Category;
import com.example.responsive_kiosk.product.entity.Menu;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MenuSaveRequestDto {

    MultipartFile imageFile;
    String imagePath;
    String name;
    String price;
    String description;
    Category category;

    @Builder
    public MenuSaveRequestDto(MultipartFile imageFile, String imagePath, String name, String price, Category category) {
        this.imageFile = imageFile;
        this.imagePath = imagePath;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public Menu toEntity() {
        return Menu.builder()
                .imagePath(imagePath)
                .name(name)
                .price(price)
                .description(description)
                .category(category)
                .build();

    }
}
