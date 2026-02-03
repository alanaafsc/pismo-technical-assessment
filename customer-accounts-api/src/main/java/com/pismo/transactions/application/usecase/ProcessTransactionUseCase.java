package com.pismo.transactions.application.usecase;

import com.pismo.transactions.domain.exceptions.NoAvailableCreditLimitException;
import com.pismo.transactions.domain.model.Account;
import com.pismo.transactions.domain.model.Transaction;
import com.pismo.transactions.domain.ports.AccountRepositoryPort;
import com.pismo.transactions.domain.exceptions.AccountNotFoundException;
import com.pismo.transactions.domain.ports.TransactionRepositoryPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class ProcessTransactionUseCase {

    private final TransactionRepositoryPort transactionRepository;
    private final AccountRepositoryPort accountRepository;

    @Transactional
    public Transaction execute(Long accountId, Integer operationTypeId, BigDecimal amount) {
        var account = accountRepository.findById(accountId)
                .orElseThrow(AccountNotFoundException::new);

        Transaction transaction = new Transaction(null, accountId, operationTypeId, amount);
        BigDecimal availableCreditLimit = handleAvailableCreditLimit(account, transaction);
        accountRepository.updateAvailableCreditLimit(accountId, availableCreditLimit);
        return transactionRepository.save(transaction);
    }

    private BigDecimal handleAvailableCreditLimit (Account account, Transaction transaction) {
        BigDecimal availableCreditLimit = account.getAvailableCreditLimit().add(transaction.getAmount());
        if(availableCreditLimit.compareTo(BigDecimal.ZERO) < 0) {
            throw new NoAvailableCreditLimitException();
        }
        return availableCreditLimit;
    }


}
