package com.pismo.transactions.application.usecase;

import com.pismo.transactions.domain.model.Account;
import com.pismo.transactions.domain.ports.AccountRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateAccountUseCase {
    private final AccountRepositoryPort accountRepository;

    public Account execute(String documentNumber) {
        if(accountRepository.existsByDocumentNumber(documentNumber)) {
            throw new RuntimeException("Account already exists");
        }
        return accountRepository.save(new Account(null, documentNumber));
    }
}
