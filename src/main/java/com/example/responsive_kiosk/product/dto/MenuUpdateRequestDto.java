package com.example.responsive_kiosk.product.dto;

import com.example.responsive_kiosk.product.entity.Category;
import lombok.Data;

@Data
public class MenuUpdateRequestDto {

    String imagePath;
    String name;
    String price;
    String description;
    Category category;
}