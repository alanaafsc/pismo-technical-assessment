package com.pismo.transactions.infrastructure.adapters.in.web;

import com.pismo.transactions.application.usecase.ProcessTransactionUseCase;
import com.pismo.transactions.domain.model.Transaction;
import com.pismo.transactions.infrastructure.adapters.in.web.dto.TransactionRequestDTO;
import com.pismo.transactions.infrastructure.adapters.in.web.dto.TransactionResponseDTO;
import com.pismo.transactions.infrastructure.adapters.in.web.mapper.TransactionMapper;
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
public class TransactionController {
    private final ProcessTransactionUseCase processTransactionUseCase;
    private final TransactionMapper transactionMapper;

    @PostMapping
    public ResponseEntity<TransactionResponseDTO> create(@RequestBody TransactionRequestDTO request) {
        Transaction transaction = processTransactionUseCase.execute(
                request.accountId(),
                request.operationTypeId(),
                request.amount()
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(transactionMapper.toDTO(transaction));
    }
}
