package com.pismo.transactions.domain.exceptions;

import com.pismo.transactions.domain.exceptions.enums.ErrorMessages;
import org.springframework.http.HttpStatus;

public class AccountNotFoundException extends BusinessException {
    public AccountNotFoundException() {
        super(ErrorMessages.ACCOUNT_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
    }
}
