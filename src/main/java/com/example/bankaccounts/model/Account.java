package com.example.bankaccounts.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Check;

import java.math.BigDecimal;

@Entity
@Table
@Getter
@Setter
@Check(constraints = "balance >= 0.0")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Customer customer;

    @Column(nullable = false)
    private BigDecimal balance;
}
