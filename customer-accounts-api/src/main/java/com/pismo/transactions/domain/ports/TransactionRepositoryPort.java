package com.pismo.transactions.domain.ports;

import com.pismo.transactions.domain.model.Transaction;

public interface TransactionRepositoryPort {
    Transaction save(Transaction transaction);
}
