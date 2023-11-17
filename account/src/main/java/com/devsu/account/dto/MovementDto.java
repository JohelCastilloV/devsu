package com.devsu.account.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Setter @Getter @ToString
public class MovementDto {
    private Long accountId;
    private BigDecimal amount;
    private Boolean status;
}
