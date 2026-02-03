package com.pismo.transactions.infrastructure.adapters.out.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "Accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Account_ID")
    private Long accountId;

    @Column(name = "Document_Number", unique = true, nullable = false)
    private String documentNumber;

    @Column(name = "Available_Credit_Limit")
    @Positive(message = "Price must be strictly positive")
    private BigDecimal availableCreditLimit;
}
