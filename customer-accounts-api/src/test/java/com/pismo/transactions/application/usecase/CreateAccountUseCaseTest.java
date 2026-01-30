package com.pismo.transactions.application.usecase;

import com.pismo.transactions.domain.exceptions.AccountAlreadyExistsException;
import com.pismo.transactions.domain.model.Account;
import com.pismo.transactions.domain.ports.AccountRepositoryPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateAccountUseCaseTest {

    @Mock
    private AccountRepositoryPort accountRepository;

    @InjectMocks
    private CreateAccountUseCase useCase;

    @Test
    @DisplayName("Should create account when document number is unique")
    void shouldCreateAccountSuccess() {
        String doc = "12345678900";
        when(accountRepository.existsByDocumentNumber(doc)).thenReturn(false);
        when(accountRepository.save(any(Account.class))).thenReturn(new Account(1L, doc));

        Account result = useCase.execute(doc);

        assertNotNull(result.getAccountId());
        assertEquals(doc, result.getDocumentNumber());
        verify(accountRepository).save(any(Account.class));
    }

    @Test
    @DisplayName("Should throw exception when account already exists")
    void shouldThrowExceptionWhenDuplicate() {
        String doc = "12345678900";
        when(accountRepository.existsByDocumentNumber(doc)).thenReturn(true);

        assertThrows(AccountAlreadyExistsException.class, () -> useCase.execute(doc));
        verify(accountRepository, never()).save(any());
    }
}
