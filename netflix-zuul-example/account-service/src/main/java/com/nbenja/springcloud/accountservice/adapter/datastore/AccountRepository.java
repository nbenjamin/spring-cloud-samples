package com.nbenja.springcloud.accountservice.adapter.datastore;

import com.nbenja.springcloud.accountservice.domain.Account;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface AccountRepository extends CrudRepository<Account, Long> {

    Optional<Account> findByAccountNumber(Long accountNumber);
}
