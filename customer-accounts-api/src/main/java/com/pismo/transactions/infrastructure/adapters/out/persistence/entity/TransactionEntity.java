package com.pismo.transactions.infrastructure.adapters.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Transaction_ID")
    private Long transactionId;

    @Column(name = "Account_ID", nullable = false)
    private Long accountId;

    @Column(name = "OperationType_ID", nullable = false)
    private Integer operationTypeId;

    @Column(name = "Amount", nullable = false)
    private BigDecimal amount;

    /**
     * Using explicit @Column mapping to ensure compatibility with the naming
     * convention defined in the project specification (PDF), bypassing
     * Hibernate's default snake_case translation to match the PostgreSQL schema.
     */
    @Column(name = "EventDate", nullable = false, columnDefinition = "TIMESTAMP(6)")
    private LocalDateTime eventDate;
}
