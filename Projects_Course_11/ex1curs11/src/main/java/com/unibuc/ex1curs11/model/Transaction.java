package com.unibuc.ex1curs11.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;

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
