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
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String goalName;

    private BigDecimal targetAmount;

    private BigDecimal savedAmount;

    private LocalDate deadline;

    @ManyToOne
    @JoinColumn(name = "app_user_id")
    private User user;

}
