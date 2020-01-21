package com.nbenja.springcloudstreamdemo.controller;

import com.nbenja.springcloudstreamdemo.config.CustomerMessageProducer;
import com.nbenja.springcloudstreamdemo.domain.Customer;
import com.nbenja.springcloudstreamdemo.repository.CustomerRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
public class MessageController {


    private CustomerMessageProducer customerMessageProducer;
    private CustomerRepository customerRepository;

    public MessageController(CustomerMessageProducer customerMessageProducer, CustomerRepository customerRepository) {
        this.customerMessageProducer = customerMessageProducer;
        this.customerRepository = customerRepository;
    }

    @GetMapping(value = "/customers", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Customer> getCustomers() {
        return Flux.interval(Duration.ofMillis(50), Duration.ofSeconds(5)).map(c -> this.customerRepository.findAll()).flatMap( c ->c);
    }


    @PostMapping(value = "/customers")
    public ResponseEntity saveCustomer(@RequestBody Customer customer) {
        System.out.println("Adding new Customer" + customer);
        customerMessageProducer.addNewCustomers(customer);
        return ResponseEntity.ok().build();
    }
}
