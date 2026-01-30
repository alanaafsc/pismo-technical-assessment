package com.pismo.transactions.infrastructure.adapters.out.persistence;

import com.pismo.transactions.domain.model.Transaction;
import com.pismo.transactions.domain.ports.TransactionRepositoryPort;
import com.pismo.transactions.infrastructure.adapters.in.web.mapper.TransactionMapper;
import com.pismo.transactions.infrastructure.adapters.out.persistence.entity.TransactionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransactionPersistenceAdapter implements TransactionRepositoryPort {
    private final SpringDataTransactionRepository repository;
    private final TransactionMapper transactionMapper;

    @Override
    public Transaction save(Transaction transaction) {
        TransactionEntity entity = transactionMapper.toEntity(transaction);

        TransactionEntity savedEntity = repository.save(entity);

        return transactionMapper.toDomain(savedEntity);
    }
}
