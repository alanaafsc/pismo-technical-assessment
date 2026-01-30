package com.pismo.transactions.infrastructure.adapters.in.web.mapper;

import com.pismo.transactions.domain.model.Account;
import com.pismo.transactions.infrastructure.adapters.in.web.dto.AccountResponseDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class AccountMapperTest {

    private final AccountMapper mapper = Mappers.getMapper(AccountMapper.class);

    @Test
    @DisplayName("Should map Account Domain to AccountResponseDTO correctly")
    void shouldMapDomainToDto() {
        Long accountId = 1L;
        String documentNumber = "12345678900";
        Account domain = new Account(accountId, documentNumber);

        AccountResponseDTO dto = mapper.toDTO(domain);

        assertNotNull(dto);
        assertEquals(accountId, dto.accountId());
        assertEquals(documentNumber, dto.documentNumber());
    }

    @Test
    @DisplayName("Should return null when domain object is null")
    void shouldReturnNullWhenSourceIsNull() {
        AccountResponseDTO dto = mapper.toDTO(null);

        assertNull(dto);
    }
}
