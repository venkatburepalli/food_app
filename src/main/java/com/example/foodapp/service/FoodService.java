package com.example.foodapp.service;

import com.example.foodapp.entity.Food;
import com.example.foodapp.repository.FoodRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FoodService {
    private final FoodRepository repository;
    public FoodService(FoodRepository repository){this.repository=repository;}
    public List<Food> getAll(){return repository.findAll();}
    public List<Food> getAvailable(){return repository.findByAvailableTrue();}
    public Food getById(Long id){return repository.findById(id).orElseThrow(() -> new RuntimeException("Food not found: " + id));}
    public Food save(Food food){return repository.save(food);}
    public void delete(Long id){repository.deleteById(id);}
    public List<Food> byCategory(String category){return repository.findByCategoryIgnoreCase(category);}
}
