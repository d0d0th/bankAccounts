package com.example.bankaccounts.api;

import com.example.bankaccounts.dto.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@RequestMapping("/api/v1")
public interface TransactionApi {

    @PostMapping("/accounts/{accountId}/transactions")
    @ResponseBody
    ResponseEntity<DWTransactionDTO> createTransaction(@Valid @RequestBody AmountDTO amountDTO,
                                                       @PathVariable Long accountId,
                                                       @RequestParam TransactionTypeEnum type);
    @GetMapping("/accounts/{accountId}/transactions")
    @ResponseBody
    ResponseEntity<TransactionsResponseDTO> getTransactions
            (@PathVariable Long accountId,
             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDateTime,
             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDateTime,
             @RequestParam(required = false) @Min(value = 1,message = "page must be higher than 0") Integer page,
             @RequestParam (required = false) @Min(value = 1,message = "limit must be higher than 0") Integer limit);

}
