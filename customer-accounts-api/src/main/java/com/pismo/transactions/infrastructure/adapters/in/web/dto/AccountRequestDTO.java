package com.pismo.transactions.infrastructure.adapters.in.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record AccountRequestDTO(@JsonProperty("document_number")
                                @NotBlank(message = "The document number is required.")
                                String documentNumber) {}

