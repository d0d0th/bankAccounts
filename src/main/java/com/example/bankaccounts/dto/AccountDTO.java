package com.example.bankaccounts.dto;

import com.example.bankaccounts.model.Customer;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountDTO {
    private Long id;
    private Customer customer;
    private BigDecimal balance;
}
