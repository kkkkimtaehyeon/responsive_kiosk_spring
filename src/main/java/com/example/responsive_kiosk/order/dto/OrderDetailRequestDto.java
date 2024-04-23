package com.example.responsive_kiosk.order.dto;

import com.example.responsive_kiosk.order.entity.OrderDetails;
import com.example.responsive_kiosk.product.entity.Menu;
import lombok.Builder;
import lombok.Data;

@Data
public class OrderDetailRequestDto {
    Integer amount;
    Double price;
    String menuName;
    String temperature;

    @Builder
    public OrderDetailRequestDto (Integer amount, Double price,String menuName, String temperature) {
        this.amount = amount;
        this.price = price;
        this.menuName = menuName;
        this.temperature = temperature;
    }

    public OrderDetails toEntity(Menu menu) {
        return OrderDetails.builder()
                .menu(menu)
                .temperature(temperature)
                .price(price)
                .amount(amount)
                .build();
    }
}
