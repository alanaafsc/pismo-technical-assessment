package com.pismo.transactions.domain.model;

public class Account {

    private Long accountId;
    private String documentNumber;

    public Account(Long accountId, String documentNumber) {
        this.accountId = accountId;
        this.documentNumber = documentNumber;
    }

    public Long getAccountId() { return accountId; }
    public String getDocumentNumber() { return documentNumber; }
}
