package com.example.bankaccounts.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class AmountDTO {
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 15,fraction = 2)
    @NotNull
    private BigDecimal amount;
}
