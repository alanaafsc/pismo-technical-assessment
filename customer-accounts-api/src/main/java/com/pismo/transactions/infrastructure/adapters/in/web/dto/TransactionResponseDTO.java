package com.pismo.transactions.infrastructure.adapters.in.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

public record TransactionResponseDTO(
        @JsonProperty("transaction_id") Long transactionId,
        @JsonProperty("account_id") Long accountId,
        @JsonProperty("operation_type_id") Integer operationTypeId,
        BigDecimal amount
) {}
