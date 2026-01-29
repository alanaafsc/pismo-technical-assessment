package com.pismo.transactions.infrastructure.adapters.in.web.mapper;

import com.pismo.transactions.domain.model.Transaction;
import com.pismo.transactions.infrastructure.adapters.in.web.dto.TransactionResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    TransactionResponseDTO toDTO(Transaction transaction);
}
