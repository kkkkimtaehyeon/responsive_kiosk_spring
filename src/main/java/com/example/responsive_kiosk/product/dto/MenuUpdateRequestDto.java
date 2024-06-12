package com.example.responsive_kiosk.product.dto;

import com.example.responsive_kiosk.product.entity.Category;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MenuUpdateRequestDto {

    MultipartFile imageFile;
    String imagePath;
    String name;
    Double price;
    String description;
}
