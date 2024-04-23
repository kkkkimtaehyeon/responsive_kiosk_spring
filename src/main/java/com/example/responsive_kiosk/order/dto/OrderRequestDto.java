package com.example.responsive_kiosk.order.dto;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
public class OrderRequestDto {
    String takeout;
    Double totalPrice;
    List<OrderDetailRequestDto> orderDetailRequestDtoList;

    @Builder
    public OrderRequestDto(String takeout, Double totalPrice, List<OrderDetailRequestDto> orderDetailRequestDtoList) {
        this.takeout = takeout;
        this.totalPrice = totalPrice;
        this.orderDetailRequestDtoList = orderDetailRequestDtoList;
    };
}
