package com.pismo.transactions.infrastructure.adapters.out.persistence;

import com.pismo.transactions.domain.model.Account;
import com.pismo.transactions.domain.ports.AccountRepositoryPort;
import com.pismo.transactions.infrastructure.adapters.in.web.mapper.AccountMapper;
import com.pismo.transactions.infrastructure.adapters.out.persistence.entity.AccountEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AccountPersistenceAdapter implements AccountRepositoryPort {
    private final SpringDataAccountRepository repository;
    private final AccountMapper mapper;

    @Override
    public Account save(Account account) {
        AccountEntity entity = new AccountEntity(null, account.getDocumentNumber(), account.getAvailableCreditLimit());
        AccountEntity saved = repository.save(entity);
        return new Account(
                saved.getAccountId(),
                saved.getDocumentNumber(),
                saved.getAvailableCreditLimit()
        );
    }

    @Override
    public Optional<Account> findById(Long id) {
        return repository.findById(id)
                .map(entity -> new Account(entity.getAccountId(), entity.getDocumentNumber(),
                entity.getAvailableCreditLimit()));
    }

    @Override
    public boolean existsByDocumentNumber(String documentNumber) {
        return repository.existsByDocumentNumber(documentNumber);
    }

    @Override
    public void updateAvailableCreditLimit(Long id, BigDecimal availableCreditLimit) {
        Optional<AccountEntity> entity = repository.findById(id);
        var newEntity = entity.get();
        newEntity.setAvailableCreditLimit(availableCreditLimit);
        repository.save(newEntity);
    }
}
