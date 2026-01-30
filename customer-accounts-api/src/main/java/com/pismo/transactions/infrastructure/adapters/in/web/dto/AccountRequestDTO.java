package com.pismo.transactions.infrastructure.adapters.in.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

/*
 * For this technical assessment, I strictly followed the PDF specification which
 * defines the document as a simple String. In a production environment, I would
 * implement @CPF or custom validators to ensure data integrity and compliance.
 */
public record AccountRequestDTO(@JsonProperty("document_number")
                                @NotBlank(message = "The document number is required.")
                                String documentNumber) {}

