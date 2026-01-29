package com.pismo.transactions.infrastructure.adapters.out.persistence;

import com.pismo.transactions.domain.model.Account;
import com.pismo.transactions.domain.ports.AccountRepositoryPort;
import com.pismo.transactions.infrastructure.adapters.out.persistence.entity.AccountEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AccountPersistenceAdapter implements AccountRepositoryPort {
    private final SpringDataAccountRepository repository;

    @Override
    public Account save(Account account) {
        AccountEntity entity = new AccountEntity(null, account.getDocumentNumber());
        AccountEntity saved = repository.save(entity);
        return new Account(saved.getAccountId(), saved.getDocumentNumber());
    }

    @Override
    public Optional<Account> findById(Long id) {
        return repository.findById(id)
                .map(entity -> new Account(entity.getAccountId(), entity.getDocumentNumber()));
    }

    @Override
    public boolean existsByDocumentNumber(String documentNumber) {
        return repository.existsByDocumentNumber(documentNumber);
    }
}
