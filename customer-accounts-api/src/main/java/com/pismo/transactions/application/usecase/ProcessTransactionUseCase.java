package com.pismo.transactions.application.usecase;

import com.pismo.transactions.domain.model.Transaction;
import com.pismo.transactions.domain.ports.AccountRepositoryPort;
import com.pismo.transactions.domain.exceptions.AccountNotFoundException;
import com.pismo.transactions.domain.ports.TransactionRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class ProcessTransactionUseCase {

    private final TransactionRepositoryPort transactionRepository;
    private final AccountRepositoryPort accountRepository;

    public Transaction execute(Long accountId, Integer operationTypeId, BigDecimal amount) {
        accountRepository.findById(accountId)
                .orElseThrow(AccountNotFoundException::new);

        Transaction transaction = new Transaction(null, accountId, operationTypeId, amount);

        return transactionRepository.save(transaction);
    }
}
