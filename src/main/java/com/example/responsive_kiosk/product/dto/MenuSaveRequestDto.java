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
    String name;
    Double price;
    String description;
    String categoryName;

    @Builder
    public MenuSaveRequestDto(MultipartFile imageFile, String name, double price, String categoryName) {
        this.imageFile = imageFile;
        this.name = name;
        this.price = price;
        this.categoryName = categoryName;
    }

    public Menu toEntity(Category category, String imagePath) {
        return Menu.builder()
                .imagePath(imagePath)
                .name(name)
                .price(price)
                .description(description)
                .category(category)
                .build();
    }
}
