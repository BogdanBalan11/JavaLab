package com.unibuc.ex1curs11.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String goalName;

    @NotNull(message = "Target amount cannot be null.")
    @Digits(integer = 15, fraction = 2, message = "Target amount must be a valid monetary amount.")
    @PositiveOrZero(message = "Target amount must be zero or positive.")
    private BigDecimal targetAmount;

    @NotNull(message = "Saved amount cannot be null.")
    @Digits(integer = 15, fraction = 2, message = "Saved amount must be a valid monetary amount.")
    @PositiveOrZero(message = "Saved amount must be zero or positive.")
    private BigDecimal savedAmount;

    @NotNull(message = "Goal date cannot be null.")
    @Future(message = "Goal date must be in the future.")
    private LocalDate deadline;

    @ManyToOne
    @JoinColumn(name = "app_user_id")
    private User user;

}
