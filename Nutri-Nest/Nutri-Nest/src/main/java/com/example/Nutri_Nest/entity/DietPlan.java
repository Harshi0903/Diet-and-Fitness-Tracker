package com.example.Nutri_Nest.entity;

import com.example.Nutri_Nest.enums.MealType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class DietPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Meal type is required")
    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private MealType mealType; //breakfast, lunch, dinner, snack

    @NotNull(message = "Calories is required")
    private Double totalCalories; //daily intake of calories

    @NotNull(message = "Fiber is required")
    private  Double totalFiber;

    @NotNull(message = "Fat is required")
    private Double totalFat;

    @NotNull(message = "Protein is required")
    private Double totalProtein;

    @PastOrPresent(message = "Diet date cannot be in the future")
    private LocalDate dietDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull(message = "User is required")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "dietplan_fooditems",
            joinColumns = @JoinColumn(name = "diet_plan_id"),
            inverseJoinColumns = @JoinColumn(name = "food_item_id")
    )
    private List<FoodItem> foodItems;

}
