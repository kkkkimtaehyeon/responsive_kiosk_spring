package com.example.responsive_kiosk.order.controller;

import com.example.responsive_kiosk.order.dto.OrderDetailRequestDto;
import com.example.responsive_kiosk.order.dto.OrderRequestDto;
import com.example.responsive_kiosk.order.service.OrderService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderService orderService;
    @PostMapping("/api/orders")
    public ResponseEntity<Long> createOrder(@RequestBody OrderRequestDto requestDto) {

        return orderService.save(requestDto);
    }
}
