package com.example.bankaccounts.service;

import com.example.bankaccounts.dto.*;
import com.example.bankaccounts.model.Account;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionService {

    DWTransactionDTO createTransaction(AmountDTO amountDTO, Long accountId, TransactionTypeEnum type);
    DWTransactionDTO createTransaction(AmountDTO amountDTO, Account account, TransactionTypeEnum type);
    TransactionsResponseDTO getTransactions(Long accountId, LocalDateTime startDateTime, LocalDateTime endDateTime,
                                            Integer page, Integer limit);
}
