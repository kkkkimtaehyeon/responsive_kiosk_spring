package com.example.responsive_kiosk.size.repository;

import com.example.responsive_kiosk.size.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepository extends JpaRepository<Size, Long> {
}
