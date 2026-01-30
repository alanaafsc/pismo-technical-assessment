package com.pismo.transactions.infrastructure.adapters.out.persistence;

import com.pismo.transactions.domain.model.Transaction;
import com.pismo.transactions.TransactionsApiApplication;
import com.pismo.transactions.infrastructure.adapters.in.web.mapper.TransactionMapper;
import com.pismo.transactions.infrastructure.adapters.out.persistence.entity.TransactionEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.*;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DataJpaTest(properties = {
        "spring.flyway.enabled=false",
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "spring.jpa.show-sql=true"
})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
@Import({TransactionPersistenceAdapter.class})
@ContextConfiguration(classes = TransactionsApiApplication.class)
class TransactionPersistenceAdapterIT {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15");

    @Autowired
    private TransactionPersistenceAdapter adapter;

    @MockitoBean
    private TransactionMapper mapper;

    @Test
    void shouldSaveThroughAdapter() {
        Transaction domainInput = new Transaction(123L, 1, new BigDecimal("100.00"));

        TransactionEntity entityToSave = new TransactionEntity();
        entityToSave.setAccountId(123L);
        entityToSave.setAmount(new BigDecimal("-100.00"));
        entityToSave.setOperationTypeId(1);
        entityToSave.setEventDate(LocalDateTime.now());

        Transaction domainOutput = new Transaction(123L, 1, new BigDecimal("-100.00"));
        domainOutput.setTransactionId(1L);

        when(mapper.toEntity(any(Transaction.class))).thenReturn(entityToSave);
        when(mapper.toDomain(any(TransactionEntity.class))).thenReturn(domainOutput);

        var result = adapter.save(domainInput);

        assertNotNull(result);
        assertEquals(1L, result.getTransactionId());
    }
}
