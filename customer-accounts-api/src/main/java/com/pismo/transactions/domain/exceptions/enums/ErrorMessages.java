package com.pismo.transactions.domain.exceptions.enums;

public enum ErrorMessages {
    ACCOUNT_NOT_FOUND("Account not found for the given ID"),
    ACCOUNT_ALREADY_EXISTS("An account with this document already exists"),
    INVALID_OPERATION_TYPE("The informed operation type is invalid");

    private final String message;
    ErrorMessages(String message) { this.message = message; }
    public String getMessage() { return message; }
}
