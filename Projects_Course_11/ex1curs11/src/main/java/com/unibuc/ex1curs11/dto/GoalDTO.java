package com.unibuc.ex1curs11.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class GoalDTO {
    private Long id;
    private String goalName;
    private BigDecimal targetAmount;
    private BigDecimal savedAmount;
    private LocalDate deadline;
    private Long userId;

}
