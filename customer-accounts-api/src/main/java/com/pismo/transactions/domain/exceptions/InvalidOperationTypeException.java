package com.pismo.transactions.domain.exceptions;

import com.pismo.transactions.domain.exceptions.enums.ErrorMessages;
import org.springframework.http.HttpStatus;

public class InvalidOperationTypeException extends BusinessException {
    public InvalidOperationTypeException() {
        super(ErrorMessages.INVALID_OPERATION_TYPE.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
