package com.pismo.transactions.infrastructure.adapters.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
