package com.example.foodapp.service;

import com.example.foodapp.entity.Food;
import com.example.foodapp.repository.FoodRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final FoodRepository repository;
    public DataLoader(FoodRepository repository){this.repository=repository;}
    @Override public void run(String... args){
        if(repository.count()==0){
            repository.save(new Food("Chicken Biryani","Biryani",249.0,"Hyderabadi style chicken biryani",true));
            repository.save(new Food("Paneer Butter Masala","North Indian",199.0,"Creamy paneer curry",true));
            repository.save(new Food("Masala Dosa","South Indian",99.0,"Crispy dosa with potato masala",true));
            repository.save(new Food("Veg Fried Rice","Chinese",149.0,"Wok fried rice with vegetables",true));
            repository.save(new Food("Chicken 65","Starters",179.0,"Spicy crispy chicken starter",true));
        }
    }
}
