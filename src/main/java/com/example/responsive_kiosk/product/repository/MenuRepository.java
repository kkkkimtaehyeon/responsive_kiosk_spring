package com.example.responsive_kiosk.product.repository;

import com.example.responsive_kiosk.product.entity.Category;
import com.example.responsive_kiosk.product.entity.Menu;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    Optional<Menu> findByName(String name);
    List<Menu> findByNameContaining(String keyword, Sort category);

    List<Menu> findByCategory(Category category);
}
