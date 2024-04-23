package com.example.responsive_kiosk.order.repository;

import com.example.responsive_kiosk.order.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetails, Long> {
}
