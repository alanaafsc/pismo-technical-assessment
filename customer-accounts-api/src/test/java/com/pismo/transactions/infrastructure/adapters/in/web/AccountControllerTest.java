package com.pismo.transactions.infrastructure.adapters.in.web;

import com.pismo.transactions.application.usecase.CreateAccountUseCase;
import com.pismo.transactions.domain.model.Account;
import com.pismo.transactions.domain.ports.AccountRepositoryPort;
import com.pismo.transactions.infrastructure.adapters.in.web.dto.AccountResponseDTO;
import com.pismo.transactions.infrastructure.adapters.in.web.mapper.AccountMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AccountController.class)
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CreateAccountUseCase createAccountUseCase;

    @MockitoBean
    private AccountRepositoryPort accountRepository;

    @MockitoBean
    private AccountMapper accountMapper;

    @Test
    @DisplayName("POST /accounts - Should return 201 Created")
    void shouldReturn201OnCreate() throws Exception {
        Account mockAccount = new Account(1L, "12345");
        AccountResponseDTO mockResponse = new AccountResponseDTO(1L, "12345");

        when(createAccountUseCase.execute(any())).thenReturn(mockAccount);
        when(accountMapper.toDTO(any())).thenReturn(mockResponse);

        mockMvc.perform(post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"document_number\": \"12345\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/accounts/1"))
                .andExpect(jsonPath("$.account_id").value(1));
    }

    @Test
    @DisplayName("GET /accounts/{id} - Should return 200 OK")
    void shouldReturn200OnGetById() throws Exception {
        Account mockAccount = new Account(1L, "12345");
        AccountResponseDTO mockResponse = new AccountResponseDTO(1L, "12345");

        when(accountRepository.findById(1L)).thenReturn(Optional.of(mockAccount));
        when(accountMapper.toDTO(any())).thenReturn(mockResponse);

        mockMvc.perform(get("/accounts/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.document_number").value("12345"));
    }

    @Test
    @DisplayName("GET /accounts/{id} - Should return 404 when not found")
    void shouldReturn404WhenNotFound() throws Exception {
        when(accountRepository.findById(anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(get("/accounts/99"))
                .andExpect(status().isNotFound());
    }
}
