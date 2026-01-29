package com.pismo.transactions.domain.exceptions;

import com.pismo.transactions.domain.exceptions.enums.ErrorMessages;

public class AccountAlreadyExistsException extends BusinessException {
    public AccountAlreadyExistsException() {
        super(ErrorMessages.ACCOUNT_ALREADY_EXISTS.getMessage());
    }
}
