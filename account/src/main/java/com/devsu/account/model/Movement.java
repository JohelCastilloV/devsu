package com.devsu.account.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Setter @Getter @ToString
public class Movement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate createdDate;
    private String movementType;
    private BigDecimal amount;
    private BigDecimal previousBalance;
    @ManyToOne
    private Account account;
}
