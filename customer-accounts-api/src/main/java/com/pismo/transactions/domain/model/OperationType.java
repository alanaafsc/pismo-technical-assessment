package com.pismo.transactions.domain.model;

import com.pismo.transactions.domain.exceptions.InvalidOperationTypeException;

public enum OperationType {
    PURCHASE(1, true),
    INSTALLMENT_PURCHASE(2, true),
    WITHDRAWAL(3, true),
    PAYMENT(4, false);

    private final int id;
    private final boolean isDebit;

    OperationType(int id, boolean isDebit) {
        this.id = id;
        this.isDebit = isDebit;
    }

    public static OperationType fromId(int id) {
        for (OperationType type : values()) {
            if (type.id == id) return type;
        }
        throw new InvalidOperationTypeException();
    }

    public boolean isDebit() { return isDebit; }
}
