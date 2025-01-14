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
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String billName;

    private BigDecimal amount;

    private LocalDate nextDueDate;

    private String description;

    @ManyToOne
    @JoinColumn(name = "app_user_id")
    private User user;

}
