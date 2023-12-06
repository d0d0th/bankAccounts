package com.example.bankaccounts.controller;

import com.example.bankaccounts.api.CustomerApi;
import com.example.bankaccounts.dto.CustomerDTO;
import com.example.bankaccounts.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class CustomerController implements CustomerApi {

    @Autowired
    CustomerService customerService;

    @Override
    public ResponseEntity<CustomerDTO> createCustomer(CustomerDTO customerDTO) {
        //return ResponseEntity.ok(customerService.createCustomer(customerDTO));
        return new ResponseEntity(customerService.createCustomer(customerDTO), HttpStatus.CREATED);
    }
}
