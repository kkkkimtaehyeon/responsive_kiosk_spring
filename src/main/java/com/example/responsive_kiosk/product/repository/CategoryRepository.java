package com.example.responsive_kiosk.product.repository;


import com.example.responsive_kiosk.product.entity.Category;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String categoryName);

}
