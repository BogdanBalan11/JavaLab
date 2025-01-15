package com.unibuc.ex1curs11.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Budget amount cannot be null.")
    @Digits(integer = 15, fraction = 2, message = "Budget amount must be a valid monetary amount.")
    @PositiveOrZero(message = "Budget amount must be zero or positive.")
    private BigDecimal amount;

    @NotNull(message = "Budget start date cannot be null.")
    private LocalDate startDate;

    @NotNull(message = "Budget end date cannot be null.")
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "app_user_id")
    private User user;

}
