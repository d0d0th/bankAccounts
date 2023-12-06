package com.example.bankaccounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(Long id){
        super(ErrorConstants.ERR_ACCOUNT_NOT_FOUND + " "+id);
    }
}
