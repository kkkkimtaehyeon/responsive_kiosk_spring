package com.example.responsive_kiosk.product.entity;

import com.example.responsive_kiosk.product.dto.MenuUpdateRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "image_path", nullable = true)
    private String imagePath;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private String price;

    @Column(name = "description", nullable = false)
    private String description;


    @ManyToOne
    @JoinColumn(referencedColumnName = "category_id")
    private Category category;

    @Builder
    public Menu(String imagePath, String name, String price, String description, Category category) {
        this.imagePath = imagePath;
        this.name = name;
        this.price= price;
        this.description = description;
        this.category = category;
    }

    public void update(MenuUpdateRequestDto requestDto) {
        this.imagePath = requestDto.getImagePath();
        this.name = requestDto.getName();
        this.price = requestDto.getPrice();
        this.description = requestDto.getDescription();
        this.category = requestDto.getCategory();
    }
}
