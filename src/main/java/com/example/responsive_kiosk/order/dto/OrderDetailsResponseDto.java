package com.example.responsive_kiosk.order.dto;

import com.example.responsive_kiosk.order.entity.OrderDetails;
import lombok.Data;

@Data
public class OrderDetailsResponseDto {

    Integer amount;
    Double price;
    String menuName;
    String temperature;

    public OrderDetailsResponseDto(OrderDetails details) {
        this.amount = details.getAmount();
        this.price = details.getPrice();
        this.menuName = details.getMenu().getName();
        this.temperature = details.getTemperature();
    }

}
