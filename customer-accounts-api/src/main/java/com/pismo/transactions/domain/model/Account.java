package com.pismo.transactions.domain.model;

import java.math.BigDecimal;

public class Account {

    private Long accountId;
    private String documentNumber;
    private BigDecimal availableCreditLimit;

    public Account(String documentNumber) {
        this.documentNumber = documentNumber;
        this.availableCreditLimit = new BigDecimal("5000");
    }

    public Account(Long accountId, String documentNumber, BigDecimal availableCreditLimit) {
        this.accountId = accountId;
        this.documentNumber = documentNumber;
        this.availableCreditLimit = availableCreditLimit;
    }

    public Long getAccountId() { return accountId; }
    public String getDocumentNumber() { return documentNumber; }
    public BigDecimal getAvailableCreditLimit() {
        return availableCreditLimit;
    }

}
