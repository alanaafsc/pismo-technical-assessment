package com.pismo.transactions.infrastructure.adapters.in.web.docs;

import com.pismo.transactions.infrastructure.adapters.in.web.dto.ErrorResponseDTO;
import com.pismo.transactions.infrastructure.adapters.in.web.dto.TransactionRequestDTO;
import com.pismo.transactions.infrastructure.adapters.in.web.dto.TransactionResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Transactions", description = "Endpoints for processing financial transactions")
public interface TransactionControllerAPI {

    @Operation(
            summary = "Create a new transaction",
            description = "Processes a financial transaction. Types 1, 2, and 3 result in negative amounts. Type 4 results in positive amounts."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transaction processed successfully"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Account not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input or operation type",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))
            )
    })
    ResponseEntity<TransactionResponseDTO> create(TransactionRequestDTO request);
}
