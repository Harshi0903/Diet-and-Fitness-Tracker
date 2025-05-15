package com.example.Nutri_Nest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class FoodItemDTO {
    private Long id;
    private String foodName;
    private Double calories;
    private Double protein;
    private Double fat;
    private Double fiber;

    public FoodItemDTO(Long id, String foodName, Double calories, Double protein, Double fat, Double fiber) {
        this.id = id;
        this.foodName = foodName;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.fiber = fiber;
    }

    public FoodItemDTO() {

    }
}



