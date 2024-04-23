package com.example.responsive_kiosk.order.dto;

import com.example.responsive_kiosk.order.entity.Orders;
import lombok.Builder;
import lombok.Data;

@Data
public class OrderResponseDto {
    Long id;
    String takeout;
    String createTime;
    //userInfo

    @Builder
    public OrderResponseDto(Orders orders) {
        this.id = orders.getId();
        this.takeout = orders.getTakeout();
        this.createTime = orders.getCreateTime();
    }

}
