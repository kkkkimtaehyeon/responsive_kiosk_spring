package com.example.responsive_kiosk.product.dto;

import com.example.responsive_kiosk.product.entity.Category;
import com.example.responsive_kiosk.product.entity.Menu;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.Builder;
import lombok.Data;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MenuSaveRequestDto {

    @JsonIgnore
    MultipartFile imageFile;
    String imagePath;
    String name;
    Double price;
    String description;
    Category category;
    String categoryName;

    @Builder
    public MenuSaveRequestDto(MultipartFile imageFile, String imagePath, String name, double price, Category category, String categoryName) {
        this.imageFile = imageFile;
        this.imagePath = imagePath;
        this.name = name;
        this.price = price;
        this.category = category;
        this.categoryName = categoryName;
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
