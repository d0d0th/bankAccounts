package com.example.bankaccounts.exception.handler;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;


@ControllerAdvice
public class BankAccountsExceptionHandler {

    @ExceptionHandler(SQLException.class)
    ResponseStatusException sqlExceptionHandler(SQLException ex) {
        return new ResponseStatusException(HttpStatus.CONFLICT,ex.getMessage());
    }
}
