package com.example.bankaccounts.service.impl;

import com.example.bankaccounts.dto.*;
import com.example.bankaccounts.exception.AccountNotFoundException;
import com.example.bankaccounts.exception.InsuficientBalanceException;
import com.example.bankaccounts.model.Account;
import com.example.bankaccounts.model.Transaction;
import com.example.bankaccounts.repository.AccountRepository;
import com.example.bankaccounts.repository.TransactionRepository;
import com.example.bankaccounts.service.TransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public DWTransactionDTO createTransaction(AmountDTO amountDTO, Long accountId, TransactionTypeEnum type) {
        Account account = getAccountById(accountId);
       return createTransaction(amountDTO,account,type);
    }

    @Override
    public DWTransactionDTO createTransaction(AmountDTO amountDTO, Account account, TransactionTypeEnum type) {
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setDateTime(LocalDateTime.now());

        if(type == TransactionTypeEnum.WITHDRAW){
            if(account.getBalance().compareTo(amountDTO.getAmount()) < 0){
                throw new InsuficientBalanceException();
            }
            transaction.setAmount(amountDTO.getAmount().negate());
        }else {
            transaction.setAmount(amountDTO.getAmount());
        }
        transactionRepository.save(transaction);
        account.setBalance(account.getBalance().add(transaction.getAmount()));
        accountRepository.save(account);

        return modelMapper.map(transaction, DWTransactionDTO.class);
    }

    @Override
    public TransactionsResponseDTO getTransactions(Long accountId, LocalDateTime startDateTime, LocalDateTime endDateTime,
                                                   Integer page, Integer limit) {
        if(page == null){
            page = 1;
        }
        if(limit == null){
            limit = Integer.MAX_VALUE;
        }

        Account account = getAccountById(accountId);
        Page<Transaction> transactionsPage = transactionRepository.findTransactionByAccountAndDateTimeBetween(account,
                startDateTime,endDateTime, PageRequest.of(page-1, limit));


        List<TransactionDTO> transactionDTOList = transactionsPage.get()
                .map(x->modelMapper.map(x,TransactionDTO.class))
                .collect(Collectors.toList());

        PaginationDTO paginationDTO = new PaginationDTO(page,transactionsPage.getTotalPages(),transactionsPage.getTotalElements());

        TransactionsResponseDTO transactionsResponseDTO = new TransactionsResponseDTO();
        transactionsResponseDTO.setTransactions(transactionDTOList);
        transactionsResponseDTO.setPagination(paginationDTO);


        return transactionsResponseDTO;
    }

    private Account getAccountById(Long id){
        return accountRepository.findById(id)
                .orElseThrow(()-> new AccountNotFoundException(id));
    }
}
