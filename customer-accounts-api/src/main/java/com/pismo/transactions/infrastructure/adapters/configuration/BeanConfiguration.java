package com.pismo.transactions.infrastructure.adapters.configuration;

import com.pismo.transactions.application.usecase.CreateAccountUseCase;
import com.pismo.transactions.application.usecase.ProcessTransactionUseCase;
import com.pismo.transactions.domain.ports.AccountRepositoryPort;
import com.pismo.transactions.domain.ports.TransactionRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public CreateAccountUseCase createAccountUseCase(AccountRepositoryPort repository) {
        return new CreateAccountUseCase(repository);
    }

    @Bean
    public ProcessTransactionUseCase processTransactionUseCase(
            TransactionRepositoryPort transactionRepositoryPort,
            AccountRepositoryPort accountRepositoryPort) {
        return new ProcessTransactionUseCase(transactionRepositoryPort, accountRepositoryPort);
    }
}
