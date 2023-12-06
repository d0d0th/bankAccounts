package com.example.bankaccounts.service;

import com.example.bankaccounts.dto.CustomerDTO;
import com.example.bankaccounts.model.Customer;

public interface CustomerService {
    CustomerDTO createCustomer(CustomerDTO customerDTO);
}
