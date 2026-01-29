package com.pismo.transactions.infrastructure.adapters.in.web;

import com.pismo.transactions.application.usecase.CreateAccountUseCase;
import com.pismo.transactions.domain.exceptions.AccountNotFoundException;
import com.pismo.transactions.domain.model.Account;
import com.pismo.transactions.domain.ports.AccountRepositoryPort;
import com.pismo.transactions.infrastructure.adapters.in.web.docs.AccountControllerAPI;
import com.pismo.transactions.infrastructure.adapters.in.web.dto.AccountRequestDTO;
import com.pismo.transactions.infrastructure.adapters.in.web.dto.AccountResponseDTO;
import com.pismo.transactions.infrastructure.adapters.in.web.mapper.AccountMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController implements AccountControllerAPI {
    private final CreateAccountUseCase createAccountUseCase;
    private final AccountRepositoryPort accountRepository;
    private final AccountMapper accountMapper;

    @Override
    @PostMapping
    public ResponseEntity<AccountResponseDTO> create(@Valid @RequestBody AccountRequestDTO request) {
        Account account = createAccountUseCase.execute(request.documentNumber());
        AccountResponseDTO response = accountMapper.toDTO(account);
        URI location = URI.create("/accounts/" + response.accountId());

        return ResponseEntity.created(location).body(response);
    }

    @Override
    @GetMapping("/{accountId}")
    public ResponseEntity<AccountResponseDTO> getById(@PathVariable Long accountId) {
        return accountRepository.findById(accountId)
                .map(accountMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(AccountNotFoundException::new);
    }
}
