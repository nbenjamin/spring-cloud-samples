package com.nbenja.account.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequestMapping("/")
public class AccountController {

    @GetMapping(value = "accounts/{accountId}")
    public String getAccounts(@PathVariable("accountId") Long accountId) {
        return "Hello Account";
    }
}
