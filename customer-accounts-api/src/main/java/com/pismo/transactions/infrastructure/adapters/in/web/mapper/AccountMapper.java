package com.pismo.transactions.infrastructure.adapters.in.web.mapper;

import com.pismo.transactions.domain.model.Account;
import com.pismo.transactions.infrastructure.adapters.in.web.dto.AccountResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountResponseDTO toDTO(Account account);
}
