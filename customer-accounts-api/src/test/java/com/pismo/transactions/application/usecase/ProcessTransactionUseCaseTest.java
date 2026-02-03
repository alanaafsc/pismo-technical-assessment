package com.pismo.transactions.application.usecase;

import com.pismo.transactions.domain.exceptions.AccountNotFoundException;
import com.pismo.transactions.domain.model.Account;
import com.pismo.transactions.domain.model.Transaction;
import com.pismo.transactions.domain.ports.AccountRepositoryPort;
import com.pismo.transactions.domain.ports.TransactionRepositoryPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProcessTransactionUseCaseTest {

    @Mock
    private TransactionRepositoryPort transactionRepository;

    @Mock
    private AccountRepositoryPort accountRepository;

    @InjectMocks
    private ProcessTransactionUseCase useCase;

    @Test
    @DisplayName("Should save transaction when account exists")
    void shouldSaveTransactionWhenAccountExists() {
        Long accountId = 1L;
        Integer operationTypeId = 4;
        BigDecimal amount = new BigDecimal("100.0");
        Account account = new Account("12345678900");

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));

        when(transactionRepository.save(any(Transaction.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Transaction result = useCase.execute(accountId, operationTypeId, amount);

        assertNotNull(result);
        assertEquals(amount, result.getAmount());
        verify(accountRepository).findById(accountId);
        verify(transactionRepository).save(any(Transaction.class));
    }

    @Test
    @DisplayName("Should throw AccountNotFoundException when account does not exist")
    void shouldThrowExceptionWhenAccountNotFound() {
        Long accountId = 99L;
        when(accountRepository.findById(accountId)).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, () -> {
            useCase.execute(accountId, 1, new BigDecimal("100.0"));
        });

        verify(transactionRepository, never()).save(any());
    }
}
