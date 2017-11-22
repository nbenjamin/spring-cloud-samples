package com.nbenja.springcloud.customerservice;

import com.nbenja.springcloud.customerservice.adapter.datastore.CustomerRepository;
import com.nbenja.springcloud.customerservice.domain.Customer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
		return (args) -> {
			customerRepository.save(new Customer(200L, "Ryan", "Benjamin", "400-404-2929"));
			customerRepository.save(new Customer(201L, "Adam", "Benjamin", "666-404-7787"));
		};
	}
}
