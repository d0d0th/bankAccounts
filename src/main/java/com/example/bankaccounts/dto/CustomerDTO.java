package com.example.bankaccounts.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO {
    @NotNull
    @Min(1)
    private Long id;

    @NotBlank
    private String name;
}
