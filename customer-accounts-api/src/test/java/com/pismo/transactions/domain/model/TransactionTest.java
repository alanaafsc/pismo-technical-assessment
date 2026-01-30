package com.pismo.transactions.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionTest {

    @Test
    @DisplayName("Should invert signal to negative when operation is PURCHASE")
    void shouldInvertSignalForPurchase() {
        BigDecimal amount = new BigDecimal("100.00");

        Transaction transaction = new Transaction(1L, 1, amount);

        assertEquals(new BigDecimal("-100.00"), transaction.getAmount());
    }

    @Test
    @DisplayName("Should keep positive signal when operation is PAYMENT")
    void shouldKeepPositiveForPayment() {
        BigDecimal amount = new BigDecimal("100.00");

        Transaction transaction = new Transaction(1L, 4, amount);

        assertEquals(new BigDecimal("100.00"), transaction.getAmount());
    }
}
