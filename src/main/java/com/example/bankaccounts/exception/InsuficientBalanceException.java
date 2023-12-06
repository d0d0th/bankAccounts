package com.example.bankaccounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class InsuficientBalanceException extends RuntimeException {
    public InsuficientBalanceException(){
        super(ErrorConstants.ERR_INSUFFICIENT_BALANCE);
    }
}
