package com.nbenja.springcloud.accountservice;

import com.nbenja.springcloud.accountservice.adapter.datastore.AccountRepository;
import com.nbenja.springcloud.accountservice.domain.Account;
import com.nbenja.springcloud.accountservice.domain.AccountType;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }

    @Bean
    public ApplicationRunner applicationRunner(AccountRepository accountRepository) {
        return (applicationArguments) -> {
            accountRepository.save(new Account(AccountType.CHECKING, 101L, 1001881.19));
            accountRepository.save(new Account(AccountType.SAVING, 102L, 1880399.19));
        };
    }
}
