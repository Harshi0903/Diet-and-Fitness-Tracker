package com.example.Nutri_Nest.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class GoalReportDTO {
    private Long id;
    private LocalDate reportDate;
    private Double totalCaloriesConsumed;
    private Double totalCaloriesBurned;
    private Double weightChange;
    private Long userId;
}
