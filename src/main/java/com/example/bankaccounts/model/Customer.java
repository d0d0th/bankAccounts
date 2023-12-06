package com.example.bankaccounts.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class Customer {
    @Id
    private Long id;

    @Column(nullable = false)
    @NotBlank
    private String name;

}
