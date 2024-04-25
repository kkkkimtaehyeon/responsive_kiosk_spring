package com.example.responsive_kiosk.order.dto;

import com.example.responsive_kiosk.order.entity.OrderDetails;
import com.example.responsive_kiosk.order.entity.Orders;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class OrderDetailResponseDto {

    Long id;
    Double totalPrice;
    String takeout;
    String createTime;
    List<OrderDetailsResponseDto> orderDetailDtoList;

    public OrderDetailResponseDto(Orders orders, List<OrderDetails> orderDetailList) {
        this.id = orders.getId();
        this.totalPrice = orders.getTotalPrice();
        this.createTime = orders.getCreateTime();
        this.takeout = orders.getTakeout();
        this.orderDetailDtoList = OrderDetailListToDtos(orderDetailList);


    }

    public List<OrderDetailsResponseDto> OrderDetailListToDtos(List<OrderDetails> orderDetailList) {
        List<OrderDetailsResponseDto> details = new ArrayList<>();

        for(OrderDetails orderDetails : orderDetailList) {
            details.add(new OrderDetailsResponseDto(orderDetails));
        }
        return details;
    }
}
