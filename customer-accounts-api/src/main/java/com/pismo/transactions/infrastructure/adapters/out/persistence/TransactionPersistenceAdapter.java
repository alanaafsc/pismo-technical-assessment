package com.pismo.transactions.infrastructure.adapters.out.persistence;

import com.pismo.transactions.domain.model.Transaction;
import com.pismo.transactions.domain.ports.TransactionRepositoryPort;
import com.pismo.transactions.infrastructure.adapters.out.persistence.entity.TransactionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransactionPersistenceAdapter implements TransactionRepositoryPort {
    private final SpringDataTransactionRepository repository;

    @Override
    public Transaction save(Transaction transaction) {
        TransactionEntity entity = new TransactionEntity();
        entity.setAccountId(transaction.getAccountId());
        entity.setOperationTypeId(transaction.getOperationTypeId());
        entity.setAmount(transaction.getAmount());
        entity.setEventDate(transaction.getEventDate());

        TransactionEntity saved = repository.save(entity);

        return new Transaction(saved.getAccountId(), saved.getOperationTypeId(), saved.getAmount());
    }
}
