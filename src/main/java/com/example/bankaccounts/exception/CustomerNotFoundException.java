package com.example.bankaccounts.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(Long id){
        super(ErrorConstants.ERR_CUSTOMER_NOT_FOUND + " "+id);
    }

}
