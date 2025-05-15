package com.example.Nutri_Nest.exception;

public class FoodItemNotFoundException extends RuntimeException {
    public FoodItemNotFoundException(String message) {
        super(message);
    }
}
