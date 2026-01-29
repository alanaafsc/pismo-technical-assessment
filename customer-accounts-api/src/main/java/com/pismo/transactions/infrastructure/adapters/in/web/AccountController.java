package com.pismo.transactions.infrastructure.adapters.in.web;

import com.pismo.transactions.application.usecase.CreateAccountUseCase;
import com.pismo.transactions.domain.model.Account;
import com.pismo.transactions.domain.ports.AccountRepositoryPort;
import com.pismo.transactions.infrastructure.adapters.in.web.dto.AccountRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final CreateAccountUseCase createAccountUseCase;
    private final AccountRepositoryPort accountRepository;

    @PostMapping
    public ResponseEntity<Account> create(@RequestBody AccountRequestDTO request) {
        Account account = createAccountUseCase.execute(request.documentNumber());
        return ResponseEntity.ok(account);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getById(@PathVariable Long accountId) {
        return accountRepository.findById(accountId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
