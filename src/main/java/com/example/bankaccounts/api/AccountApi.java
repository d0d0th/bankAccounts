package com.example.bankaccounts.api;

import com.example.bankaccounts.dto.AccountDTO;
import com.example.bankaccounts.dto.AmountDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1")
public interface AccountApi {

    @PostMapping("/customers/{customerId}/accounts")
    @ResponseBody
    ResponseEntity<AccountDTO> createAccount(@Valid  @RequestBody AmountDTO amountDTO, @PathVariable Long customerId);

}
