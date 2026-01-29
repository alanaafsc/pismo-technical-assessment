package com.pismo.transactions.infrastructure.adapters.in.web.docs;

import com.pismo.transactions.infrastructure.adapters.in.web.dto.AccountRequestDTO;
import com.pismo.transactions.infrastructure.adapters.in.web.dto.AccountResponseDTO;
import com.pismo.transactions.infrastructure.adapters.in.web.dto.ErrorResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Accounts", description = "Endpoints for managing customer accounts")
public interface AccountControllerAPI {

    @Operation(
            summary = "Create a new account",
            description = "Registers a new customer account based on a unique document number."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Account created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "409", description = "Account with this document already exists",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    ResponseEntity<AccountResponseDTO> create(AccountRequestDTO request);

    @Operation(
            summary = "Get account by ID",
            description = "Retrieves details of an existing account using its internal ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account found"),
            @ApiResponse(responseCode = "404", description = "Account not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    ResponseEntity<AccountResponseDTO> getById(Long accountId);
}
