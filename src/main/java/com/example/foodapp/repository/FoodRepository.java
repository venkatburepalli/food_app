package com.example.foodapp.repository;

import com.example.foodapp.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findByAvailableTrue();
    List<Food> findByCategoryIgnoreCase(String category);
}
