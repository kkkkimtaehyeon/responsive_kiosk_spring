package com.example.responsive_kiosk.order.repository;

import com.example.responsive_kiosk.order.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
}
