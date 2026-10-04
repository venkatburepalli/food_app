package com.example.foodapp.controller;

import com.example.foodapp.entity.Food;
import com.example.foodapp.service.FoodService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/foods")
@CrossOrigin(origins = "*")
public class FoodController {
    private final FoodService service;
    public FoodController(FoodService service){this.service=service;}

    @GetMapping public List<Food> all(){return service.getAll();}
    @GetMapping("/available") public List<Food> available(){return service.getAvailable();}
    @GetMapping("/category/{category}") public List<Food> category(@PathVariable String category){return service.byCategory(category);}
    @GetMapping("/{id}") public Food one(@PathVariable Long id){return service.getById(id);}
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public Food create(@Valid @RequestBody Food food){return service.save(food);}
    @PutMapping("/{id}") public Food update(@PathVariable Long id, @Valid @RequestBody Food food){
        Food current=service.getById(id); current.setName(food.getName()); current.setCategory(food.getCategory()); current.setPrice(food.getPrice()); current.setDescription(food.getDescription()); current.setAvailable(food.getAvailable()); return service.save(current);
    }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id){service.delete(id);}
}
