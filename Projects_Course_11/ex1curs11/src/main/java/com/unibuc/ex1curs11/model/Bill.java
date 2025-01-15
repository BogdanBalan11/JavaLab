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
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String billName;

    @NotNull(message = "Bill amount cannot be null.")
    @Digits(integer = 15, fraction = 2, message = "Bill amount must be a valid monetary amount.")
    @PositiveOrZero(message = "Bill amount must be zero or positive.")
    private BigDecimal amount;

    private LocalDate nextDueDate;

    private String description;

    @ManyToOne
    @JoinColumn(name = "app_user_id")
    private User user;

}
