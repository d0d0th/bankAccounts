package com.example.bankaccounts.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class CustomerIDDuplicatedException extends RuntimeException {

    public CustomerIDDuplicatedException(Long id){
        super(ErrorConstants.ERR_CUSTOMER_ID_DUPLICATED + " "+id);
    }

}
