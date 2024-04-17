package com.example.responsive_kiosk.order.entity;

import com.example.responsive_kiosk.order.dto.MenuUpdateRequestDto;
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
    @Column(name = "menu_id")
    private Long id;

    @Column(name = "menu_image", nullable = false)
    private String image;

    @Column(name = "menu_name", nullable = false)
    private String name;

    @Column(name = "menu_price", nullable = false)
    private String price;

    @ManyToOne
    @JoinColumn(referencedColumnName = "category_id")
    private Category category;

    @Builder
    public Menu(String image, String name, String price, Category category) {
        this.image = image;
        this.name = name;
        this.price= price;
        this.category = category;
    }

    public void update(MenuUpdateRequestDto requestDto) {
        this.image = requestDto.getImage();
        this.name = requestDto.getName();
        this.price = requestDto.getPrice();
        this.category = requestDto.getCategory();
    }
}
