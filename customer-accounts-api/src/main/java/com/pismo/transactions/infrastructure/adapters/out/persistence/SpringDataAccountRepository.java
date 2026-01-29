package com.pismo.transactions.infrastructure.adapters.out.persistence;

import com.pismo.transactions.infrastructure.adapters.out.persistence.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataAccountRepository extends JpaRepository<AccountEntity, Long> {
    boolean existsByDocumentNumber(String documentNumber);
}
