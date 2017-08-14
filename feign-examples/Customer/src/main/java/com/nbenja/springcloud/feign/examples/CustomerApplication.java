package com.nbenja.springcloud.feign.examples;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@SpringBootApplication
public class CustomerApplication {

  public static void main(String[] args) {
    SpringApplication.run(CustomerApplication.class, args);
  }

  @Bean
  CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
    return (args) -> {
      customerRepository.save(new Customer("Ryan","Ben"));
      customerRepository.save(new Customer("Chris", "Subin"));
      customerRepository.save(new Customer("Dave", "Morrow"));
    };
  }
}

@RestController
@RequestMapping("/customers")
class CustomerController {

  private CustomerRepository customerRepository;

  CustomerController(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @RequestMapping("/{id}")
  public Customer getCustomer(@PathVariable("id") Long id) {
    return customerRepository.findById(id).isPresent() ? customerRepository.findById(id).get() :
        null;
  }

  @RequestMapping
  public List<Customer> getCustomers() {
    List<Customer> customerList = new ArrayList<>();
    customerRepository.findAll().forEach(customerList::add);
    return customerList;
  }

  @PostMapping
  public void createCustomer(@RequestBody Customer customer) {
    customerRepository.save(customer);
  }
}


interface CustomerRepository extends CrudRepository<Customer, Long> {
}

@NoArgsConstructor
@Data
@ToString
@Entity
class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String firstName;
  private String lastName;

  Customer(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

}