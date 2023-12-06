package com.example.bankaccounts.service.impl;

import com.example.bankaccounts.dto.AccountDTO;
import com.example.bankaccounts.dto.AmountDTO;
import com.example.bankaccounts.dto.TransactionTypeEnum;
import com.example.bankaccounts.exception.AccountNotFoundException;
import com.example.bankaccounts.exception.CustomerNotFoundException;
import com.example.bankaccounts.model.Account;
import com.example.bankaccounts.model.Customer;
import com.example.bankaccounts.repository.AccountRepository;
import com.example.bankaccounts.repository.CustomerRepository;
import com.example.bankaccounts.service.AccountService;
import com.example.bankaccounts.service.CustomerService;
import com.example.bankaccounts.service.TransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public AccountDTO createAccount(AmountDTO amountDTO, Long customerId) {

        Account account = new Account();
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(()->new CustomerNotFoundException(customerId));
        account.setCustomer(customer);
        account.setBalance(BigDecimal.ZERO);
        accountRepository.save(account);
        transactionService.createTransaction(amountDTO,account.getId(), TransactionTypeEnum.DEPOSIT);
        return modelMapper.map(account,AccountDTO.class);
    }


}
