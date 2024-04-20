package com.example.responsive_kiosk.product.repository;


import com.example.responsive_kiosk.product.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
