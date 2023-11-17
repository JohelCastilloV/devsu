package com.devsu.account.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter @Setter @ToString
public class ReportDto {
    private LocalDate createdDate;
    private String name;
    private String accountNumber;
    private String accountType;
    private BigDecimal previousBalance;
    private Boolean status;
    private BigDecimal amount;
    private BigDecimal availableBalance;

    public BigDecimal getAvailableBalance(){
        if(previousBalance!=null){
            return previousBalance.add(amount);
        }
        return null;
    }
}
