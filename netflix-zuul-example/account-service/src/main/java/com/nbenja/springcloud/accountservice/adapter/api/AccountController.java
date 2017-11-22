package com.nbenja.springcloud.accountservice.adapter.api;

import com.nbenja.springcloud.accountservice.adapter.datastore.AccountRepository;
import com.nbenja.springcloud.accountservice.domain.Account;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private AccountRepository accountRepository;

    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccount(@PathVariable("accountId") Long accountId) {
        return Optional.ofNullable(accountRepository.findByAccountNumber(accountId)).get()
                .map(a -> ResponseEntity.status(HttpStatus.OK).body(a))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }
}
