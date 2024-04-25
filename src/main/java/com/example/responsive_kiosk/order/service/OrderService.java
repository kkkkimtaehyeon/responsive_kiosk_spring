package com.example.responsive_kiosk.order.service;

import com.example.responsive_kiosk.order.dto.OrderDetailRequestDto;
import com.example.responsive_kiosk.order.dto.OrderDetailResponseDto;
import com.example.responsive_kiosk.order.dto.OrderRequestDto;
import com.example.responsive_kiosk.order.dto.OrderResponseDto;
import com.example.responsive_kiosk.order.entity.OrderDetails;
import com.example.responsive_kiosk.order.entity.Orders;
import com.example.responsive_kiosk.order.repository.OrderDetailRepository;
import com.example.responsive_kiosk.order.repository.OrderRepository;
import com.example.responsive_kiosk.product.entity.Menu;
import com.example.responsive_kiosk.product.repository.MenuRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final MenuRepository menuRepository;
    private final OrderDetailRepository orderDetailRepository;

    @Transactional
    public ResponseEntity<Long> save(OrderRequestDto orderRequestDto) {
        Orders orders = orderRepository.save(Orders.builder()
                        .takeout(orderRequestDto.getTakeout())
                        .totalPrice(orderRequestDto.getTotalPrice())
                        .build());

        for(OrderDetailRequestDto orderDetailRequestDto : orderRequestDto.getOrderDetailRequestDtoList()) {
            Menu menu = menuRepository.findByName(orderDetailRequestDto.getMenuName()).orElseThrow(EntityNotFoundException::new);

            OrderDetails orderDetails = orderDetailRequestDto.toEntity(menu);
            orderDetailRepository.save(orderDetails);

            orders.addDetails(orderDetails);
        }

        return ResponseEntity.ok(orders.getId());
    }

    public ResponseEntity<List<OrderResponseDto>> getAll() {
        List<Orders> ordersList = orderRepository.findAll();
        List<OrderResponseDto> orderResponseDtoList = new ArrayList<>();

        for(Orders order : ordersList) {
            orderResponseDtoList.add(OrderResponseDto.builder().orders(order).build());
        }
        return ResponseEntity.ok(orderResponseDtoList);
    }

    @Transactional
    public ResponseEntity<OrderDetailResponseDto> getOrderDetail(Long id) {
        Orders orders = orderRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        List<OrderDetails> orderDetails = orders.getOrderDetails();
        OrderDetailResponseDto orderDetailResponseDto = new OrderDetailResponseDto(orders, orderDetails);
        return ResponseEntity.ok(orderDetailResponseDto);
    }


}
