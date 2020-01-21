package com.nbenja.springcloudstreamdemo.config;


import com.nbenja.springcloudstreamdemo.domain.Customer;
import com.nbenja.springcloudstreamdemo.repository.CustomerRepository;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Source;

@EnableBinding(Source.class)
public class CustomerMessageConsumer {

    private CustomerRepository customerRepository;

    public CustomerMessageConsumer(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @StreamListener(Source.OUTPUT)
    public void createCustomers(Customer customer){
        System.out.println("Add Customer Event Received - " + customer);
        this.customerRepository.save(customer).subscribe();
    }

}
