package com.pismo.transactions.domain.ports;

import com.pismo.transactions.domain.model.Account;

import java.math.BigDecimal;
import java.util.Optional;

public interface AccountRepositoryPort {
    Account save(Account account);
    Optional<Account> findById(Long id);
    boolean existsByDocumentNumber(String documentNumber);
    void updateAvailableCreditLimit(Long id, BigDecimal availableCreditLimit);
}
