package com.pismo.transactions.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
    private Long transactionId;
    private Long accountId;
    private Integer operationTypeId;
    private BigDecimal amount;
    private LocalDateTime eventDate;

    public Transaction(Long accountId, Integer operationTypeId, BigDecimal amount) {
        this.accountId = accountId;
        this.operationTypeId = operationTypeId;
        this.amount = formatAmountByOperation(operationTypeId, amount);
        this.eventDate = LocalDateTime.now();
    }

    private BigDecimal formatAmountByOperation(Integer typeId, BigDecimal value) {
        if(value == null) return BigDecimal.ZERO;
        return (typeId == 4) ? value.abs() : value.abs().negate();
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
