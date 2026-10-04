package com.example.foodapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "foods")
public class Food {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Column(nullable = false)
    private String name;

    @NotBlank @Column(nullable = false)
    private String category;

    @Positive @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Boolean available = true;

    public Food() {}
    public Food(String name, String category, Double price, String description, Boolean available) {
        this.name=name; this.category=category; this.price=price; this.description=description; this.available=available;
    }
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getName(){return name;} public void setName(String name){this.name=name;}
    public String getCategory(){return category;} public void setCategory(String category){this.category=category;}
    public Double getPrice(){return price;} public void setPrice(Double price){this.price=price;}
    public String getDescription(){return description;} public void setDescription(String description){this.description=description;}
    public Boolean getAvailable(){return available;} public void setAvailable(Boolean available){this.available=available;}
}
