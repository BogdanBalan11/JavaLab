package com.unibuc.ex1curs11.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class BillDTO {
    private Long id;
    private String billName;
    private BigDecimal amount;
    private LocalDate nextDueDate;
    private String description;
    private Long userId;

}
