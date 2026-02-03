package com.pismo.transactions.infrastructure.adapters.in.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record AccountResponseDTO(@JsonProperty("account_id") Long accountId,
                                 @JsonProperty("document_number") String documentNumber,
                                 @JsonProperty("available_credit_limit") BigDecimal availableCreditLimit
                                 ) {}
