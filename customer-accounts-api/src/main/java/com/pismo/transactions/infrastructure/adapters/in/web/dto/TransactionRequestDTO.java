package com.pismo.transactions.infrastructure.adapters.in.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record TransactionRequestDTO (
        @JsonProperty("account_id") Long accountId,
        @JsonProperty("operation_type_id") Integer operationTypeId,
        BigDecimal amount
) {}