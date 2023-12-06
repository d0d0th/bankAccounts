package com.example.bankaccounts.repository;

import com.example.bankaccounts.model.Account;
import com.example.bankaccounts.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> , PagingAndSortingRepository<Transaction,Long> {
    Page<Transaction> findTransactionByAccountAndDateTimeBetween(Account account, LocalDateTime startDate,
                                                                 LocalDateTime endDate, Pageable pageable);




}


