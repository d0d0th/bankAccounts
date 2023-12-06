package com.example.bankaccounts.service;

import com.example.bankaccounts.dto.AccountDTO;
import com.example.bankaccounts.dto.AmountDTO;
import com.example.bankaccounts.model.Account;

public interface AccountService {
    AccountDTO createAccount(AmountDTO amountDTO, Long customerId);
}
