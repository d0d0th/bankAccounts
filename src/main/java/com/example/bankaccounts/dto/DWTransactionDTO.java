package com.example.bankaccounts.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class DWTransactionDTO {
    private Long id;
    private AccountDTO account;
    private BigDecimal amount;
    private LocalDateTime dateTime;
}
