package com.nbenja.customer.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 */
@RestController
@RequestMapping("/")
public class CustomerController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/customers/{accountId}")
    public String getCustomerDetails(@PathVariable("accountId") Long accountId) {
        String accountResponse = restTemplate.getForObject("http://localhost:8089/accounts/"+ accountId, String.class);
        return "Hello Customer " + accountResponse;
    }
}
