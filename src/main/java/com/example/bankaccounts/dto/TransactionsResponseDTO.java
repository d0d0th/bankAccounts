package com.example.bankaccounts.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TransactionsResponseDTO {
    private List<TransactionDTO> transactions;
    private PaginationDTO pagination;
}
