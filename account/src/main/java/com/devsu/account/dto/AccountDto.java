package com.devsu.account.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter @Setter @ToString
public class AccountDto {
    private Long clientId;
    private String accountNumber;
    private String accountType;
    private BigDecimal balance;
    private Boolean status;
}
