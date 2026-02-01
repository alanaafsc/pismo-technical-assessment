package com.pismo.transactions.infrastructure.adapters.in.web;

import com.pismo.transactions.application.usecase.ProcessTransactionUseCase;
import com.pismo.transactions.domain.model.Transaction;
import com.pismo.transactions.infrastructure.adapters.in.web.dto.TransactionResponseDTO;
import com.pismo.transactions.infrastructure.adapters.in.web.mapper.TransactionMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionController.class)
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProcessTransactionUseCase processTransactionUseCase;

    @MockitoBean
    private TransactionMapper transactionMapper;

    @Test
    @DisplayName("Should return 201 Created and the transaction details when request is valid")
    void shouldReturn201ForValidTransaction() throws Exception {
        Long transactionId = 1L;
        Long accountId = 1L;
        Integer opTypeId = 1;
        BigDecimal amount = new BigDecimal("100.50");

        Transaction mockDomain = new Transaction(transactionId, accountId, opTypeId, amount);

        TransactionResponseDTO mockResponse = new TransactionResponseDTO(
                100L,
                accountId,
                opTypeId,
                amount.negate()
        );

        when(processTransactionUseCase.execute(eq(accountId), eq(opTypeId), any(BigDecimal.class)))
                .thenReturn(mockDomain);

        when(transactionMapper.toDTO(any(Transaction.class)))
                .thenReturn(mockResponse);

        String jsonRequest = """
                {
                    "account_id": 1,
                    "operation_type_id": 1,
                    "amount": 100.50
                }
                """;

        mockMvc.perform(post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.transaction_id").value(100))
                .andExpect(jsonPath("$.account_id").value(1))
                .andExpect(jsonPath("$.operation_type_id").value(1))
                .andExpect(jsonPath("$.amount").value(-100.50));
    }

    @Test
    @DisplayName("Should return 400 Bad Request when input data is invalid")
    void shouldReturn400ForInvalidInput() throws Exception {
        String invalidJson = """
                {
                    "account_id": null,
                    "operation_type_id": 1
                }
                """;

        mockMvc.perform(post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest());
    }
}