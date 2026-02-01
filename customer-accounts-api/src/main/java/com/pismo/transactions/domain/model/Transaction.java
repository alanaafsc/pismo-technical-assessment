package com.pismo.transactions.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
    private Long transactionId;
    private Long accountId;
    private Integer operationTypeId;
    private BigDecimal amount;
    private LocalDateTime eventDate;

    public Transaction(Long transactionId, Long accountId, Integer operationTypeId,
                       BigDecimal amount) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.operationTypeId = operationTypeId;
        this.eventDate = LocalDateTime.now();
        OperationType type = OperationType.fromId(operationTypeId);
        this.amount = type.isDebit() ? amount.abs().negate() : amount.abs();
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public Integer getOperationTypeId() {
        return operationTypeId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

}
