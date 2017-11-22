package com.nbenja.springcloud.customerservice.adapter.datastore;

import com.nbenja.springcloud.customerservice.domain.Customer;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CustomerRepository extends MongoRepository<Customer, String>{

    Optional<Customer> findByFirstName(String firstName);

    Optional<Customer> findByCustomerId(Long customerId);
}
