package com.pismo.transactions.domain.exceptions;

import com.pismo.transactions.domain.exceptions.enums.ErrorMessages;

public class AccountNotFoundException extends BusinessException {
    public AccountNotFoundException() {
        super(ErrorMessages.ACCOUNT_NOT_FOUND.getMessage());
    }
}
