package com.pismo.transactions.infrastructure.adapters.out.persistence;

import com.pismo.transactions.infrastructure.adapters.out.persistence.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataTransactionRepository extends JpaRepository<TransactionEntity, Long> {

}
