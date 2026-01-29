package com.pismo.transactions.infrastructure.adapters.in.web;

import com.pismo.transactions.application.usecase.ProcessTransactionUseCase;
import com.pismo.transactions.domain.model.Transaction;
import com.pismo.transactions.infrastructure.adapters.in.web.docs.TransactionControllerAPI;
import com.pismo.transactions.infrastructure.adapters.in.web.dto.TransactionRequestDTO;
import com.pismo.transactions.infrastructure.adapters.in.web.dto.TransactionResponseDTO;
import com.pismo.transactions.infrastructure.adapters.in.web.mapper.TransactionMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController implements TransactionControllerAPI {
    private final ProcessTransactionUseCase processTransactionUseCase;
    private final TransactionMapper transactionMapper;

    @Override
    @PostMapping
    public ResponseEntity<TransactionResponseDTO> create(@Valid @RequestBody TransactionRequestDTO request) {
        var transaction = processTransactionUseCase.execute(
                request.accountId(),
                request.operationTypeId(),
                request.amount()
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(transactionMapper.toDTO(transaction));
    }
}
