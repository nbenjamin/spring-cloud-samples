package com.nbenja.springcloud.customerservice.adapter.api;

import com.nbenja.springcloud.customerservice.adapter.datastore.CustomerRepository;
import com.nbenja.springcloud.customerservice.domain.Customer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> findCustomerId(@PathVariable("customerId") Long customerId) {
        return Optional.ofNullable(customerRepository.findByCustomerId(customerId).get()).map
                (c -> ResponseEntity.status(HttpStatus.OK).body(c)).orElse(ResponseEntity.status
                (HttpStatus.NOT_FOUND).body(null));
    }
}
