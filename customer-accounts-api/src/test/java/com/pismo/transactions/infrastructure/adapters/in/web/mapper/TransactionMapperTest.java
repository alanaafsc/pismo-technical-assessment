package com.pismo.transactions.infrastructure.adapters.in.web.mapper;

import com.pismo.transactions.domain.model.Transaction;
import com.pismo.transactions.infrastructure.adapters.out.persistence.entity.TransactionEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionMapperTest {

    private final TransactionMapper mapper = Mappers.getMapper(TransactionMapper.class);

    @Test
    @DisplayName("Should convert Entity to Domain (used after saving to DB)")
    void shouldMapEntityToDomain() {
        TransactionEntity entity = new TransactionEntity();
        entity.setTransactionId(100L);
        entity.setAccountId(1L);
        entity.setOperationTypeId(1);
        entity.setAmount(new BigDecimal("-100.00"));
        entity.setEventDate(LocalDateTime.now());

        Transaction domain = mapper.toDomain(entity);

        assertEquals(entity.getTransactionId(), domain.getTransactionId());
        assertEquals(entity.getAmount(), domain.getAmount());
    }

    @Test
    @DisplayName("Should map Domain to Entity correctly")
    void shouldMapDomainToEntity() {
        Transaction domain = new Transaction(1L, 1, new BigDecimal("100.00"));

        TransactionEntity entity = mapper.toEntity(domain);

        assertEquals(domain.getAccountId(), entity.getAccountId());
        assertEquals(domain.getAmount(), entity.getAmount());
    }
}
