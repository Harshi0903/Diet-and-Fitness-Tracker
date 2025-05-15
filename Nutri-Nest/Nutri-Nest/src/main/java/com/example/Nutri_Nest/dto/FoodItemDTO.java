package com.example.Nutri_Nest.dto;

import lombok.Data;

@Data
public class FoodItemDTO {
    private Long id;
    private String foodName;
    private Double calories;
    private Double protein;
    private Double fat;
    private Double fiber;
}
