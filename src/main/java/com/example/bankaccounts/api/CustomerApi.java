package com.example.bankaccounts.api;

import com.example.bankaccounts.dto.CustomerDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/api/v1")
public interface CustomerApi {
    @PostMapping("/customers")
    @ResponseBody
    ResponseEntity<CustomerDTO> createCustomer(@Valid @RequestBody CustomerDTO customer);
}
