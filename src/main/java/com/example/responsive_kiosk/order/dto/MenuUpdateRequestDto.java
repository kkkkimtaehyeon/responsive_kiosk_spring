package com.example.responsive_kiosk.order.dto;

import com.example.responsive_kiosk.order.entity.Category;
import lombok.Data;

@Data
public class MenuUpdateRequestDto {

    String image;
    String name;
    String price;
    Long categoryId;
    Category category;
}
