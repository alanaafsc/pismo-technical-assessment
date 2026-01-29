package com.pismo.transactions.infrastructure.adapters.in.web;

import com.pismo.transactions.application.usecase.CreateAccountUseCase;
import com.pismo.transactions.domain.exceptions.AccountNotFoundException;
import com.pismo.transactions.domain.model.Account;
import com.pismo.transactions.domain.ports.AccountRepositoryPort;
import com.pismo.transactions.infrastructure.adapters.in.web.dto.AccountRequestDTO;
import com.pismo.transactions.infrastructure.adapters.in.web.dto.AccountResponseDTO;
import com.pismo.transactions.infrastructure.adapters.in.web.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final CreateAccountUseCase createAccountUseCase;
    private final AccountRepositoryPort accountRepository;
    private final AccountMapper accountMapper;

    @PostMapping
    public ResponseEntity<AccountResponseDTO> create(@RequestBody AccountRequestDTO request) {
        Account account = createAccountUseCase.execute(request.documentNumber());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(accountMapper.toDTO(account));
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountResponseDTO> getById(@PathVariable Long accountId) {
        return accountRepository.findById(accountId)
                .map(accountMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(AccountNotFoundException::new);
    }
}
