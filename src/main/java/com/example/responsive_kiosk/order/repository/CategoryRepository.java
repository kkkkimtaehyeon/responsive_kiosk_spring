package com.example.responsive_kiosk.order.repository;


import com.example.responsive_kiosk.order.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
