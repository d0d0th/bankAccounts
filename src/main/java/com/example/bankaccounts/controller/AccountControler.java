package com.example.bankaccounts.controller;

import com.example.bankaccounts.api.AccountApi;
import com.example.bankaccounts.dto.AccountDTO;
import com.example.bankaccounts.dto.AmountDTO;
import com.example.bankaccounts.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AccountControler implements AccountApi {
    @Autowired
    AccountService accountService;

    @Override
    public ResponseEntity<AccountDTO> createAccount(AmountDTO amountDTO, Long customerId) {
        AccountDTO accountDTO = accountService.createAccount(amountDTO,customerId);
        return new ResponseEntity(accountService.createAccount(amountDTO,customerId), HttpStatus.CREATED);
    }
}
