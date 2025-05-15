package com.example.Nutri_Nest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data

public class FoodItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String foodName;

    // Nutritional values per 100g
    private Double calories;
    private Double protein;
    private Double fat;
    private Double fiber;

    public FoodItem(Long id, String foodName, Double calories, Double protein, Double fat, Double fiber) {
        this.id = id;
        this.foodName = foodName;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.fiber = fiber;
    }

    public FoodItem(){

    }

}
