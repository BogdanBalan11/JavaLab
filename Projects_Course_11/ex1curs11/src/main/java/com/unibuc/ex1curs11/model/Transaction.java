package com.unibuc.ex1curs11.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Transaction Amount cannot be null.")
    @Digits(integer = 15, fraction = 2, message = "Transaction Amount must be a valid monetary amount.")
    @PositiveOrZero(message = "Transaction Amount must be zero or positive.")
    private BigDecimal amount;

    @NotNull(message = "Transaction date cannot be null.")
    @PastOrPresent(message = "Transaction date must be today or in the past.")
    private LocalDate transactionDate;

    private String description;

    // Could be "INCOME" or "EXPENSE"
    private String transactionType;

    @ManyToOne
    @JoinColumn(name = "app_user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
