package com.example.responsive_kiosk.order.repository;

import com.example.responsive_kiosk.order.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
