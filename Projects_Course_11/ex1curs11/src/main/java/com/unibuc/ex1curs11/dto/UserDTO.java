package com.unibuc.ex1curs11.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserDTO {
    private Long id;
    private String email;
    private String name;
    private BigDecimal balance;

}
