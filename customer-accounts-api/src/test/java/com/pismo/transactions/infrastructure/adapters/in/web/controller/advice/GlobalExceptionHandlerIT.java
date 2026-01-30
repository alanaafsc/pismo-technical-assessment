package com.pismo.transactions.infrastructure.adapters.in.web.controller.advice;

import com.pismo.transactions.application.usecase.CreateAccountUseCase;
import com.pismo.transactions.application.usecase.ProcessTransactionUseCase;
import com.pismo.transactions.domain.exceptions.AccountAlreadyExistsException;
import com.pismo.transactions.domain.exceptions.enums.ErrorMessages;
import com.pismo.transactions.domain.ports.AccountRepositoryPort;
import com.pismo.transactions.infrastructure.adapters.in.web.AccountController;
import com.pismo.transactions.infrastructure.adapters.in.web.TransactionController;
import com.pismo.transactions.infrastructure.adapters.in.web.mapper.AccountMapper;
import com.pismo.transactions.infrastructure.adapters.in.web.mapper.TransactionMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({AccountController.class, TransactionController.class})
class GlobalExceptionHandlerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CreateAccountUseCase createAccountUseCase;

    @MockitoBean
    private ProcessTransactionUseCase processTransactionUseCase;

    @MockitoBean
    private AccountRepositoryPort accountRepositoryPort;

    @MockitoBean
    private AccountMapper accountMapper;

    @MockitoBean
    private TransactionMapper transactionMapper;


    @Test
    @DisplayName("1. Should handle BusinessException (409 Conflict)")
    void shouldHandleBusinessException() throws Exception {
        when(createAccountUseCase.execute(any()))
                .thenThrow(new AccountAlreadyExistsException());

        mockMvc.perform(post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"document_number\": \"12345678900\"}"))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value(ErrorMessages.ACCOUNT_ALREADY_EXISTS.getMessage()));
    }

    @Test
    @DisplayName("2. Should handle MethodArgumentNotValidException (400 Bad Request)")
    void shouldHandleValidation() throws Exception {
        mockMvc.perform(post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"account_id\": 1, \"operation_type_id\": 1, \"amount\": null}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    @DisplayName("3. Should handle DataIntegrityViolationException (400 Bad Request)")
    void shouldHandleDatabaseIntegrity() throws Exception {
        when(processTransactionUseCase.execute(any(Long.class), any(Integer.class), any(BigDecimal.class)))
                .thenThrow(new DataIntegrityViolationException("FK Violation"));

        mockMvc.perform(post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"account_id\": 99, \"operation_type_id\": 1, \"amount\": 100.0}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Database integrity violation: check if account or operation type exists."));
    }

    @Test
    @DisplayName("4. Should handle Generic Exception (500 Internal Server Error)")
    void shouldHandleGenericException() throws Exception {
        when(createAccountUseCase.execute(any()))
                .thenThrow(new RuntimeException("Unexpected error"));

        mockMvc.perform(post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"document_number\": \"12345678900\"}"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message").value("An unexpected error occurred"));
    }
}
