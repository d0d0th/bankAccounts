package com.example.bankaccounts.controller;

import com.example.bankaccounts.api.TransactionApi;
import com.example.bankaccounts.dto.*;
import com.example.bankaccounts.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class TransactionController implements TransactionApi {
    @Autowired
    TransactionService transactionService;
    @Override
    public ResponseEntity<DWTransactionDTO> createTransaction(AmountDTO amountDTO, Long accountId, TransactionTypeEnum type) {
        DWTransactionDTO transactionDTO = transactionService.createTransaction(amountDTO,accountId,type);
        return new ResponseEntity(transactionDTO, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<TransactionsResponseDTO> getTransactions(Long accountId, LocalDateTime startDateTime,
                                                                   LocalDateTime endDateTime, Integer page, Integer limit) {
        TransactionsResponseDTO transactionDTOList = transactionService
                .getTransactions(accountId,startDateTime,endDateTime, page, limit);
        return ResponseEntity.ok(transactionDTOList);
    }
}
