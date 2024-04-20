package com.example.responsive_kiosk.product.repository;

import com.example.responsive_kiosk.product.entity.Menu;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findByNameContaining(String keyword, Sort category);
}
