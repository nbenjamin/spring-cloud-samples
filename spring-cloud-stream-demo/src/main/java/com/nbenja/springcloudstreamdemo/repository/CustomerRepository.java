package com.nbenja.springcloudstreamdemo.repository;

import com.nbenja.springcloudstreamdemo.domain.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerRepository extends ReactiveMongoRepository<Customer, String> {

    Flux<Customer> findAll();
    Mono<Customer> save(Customer entity);
}
