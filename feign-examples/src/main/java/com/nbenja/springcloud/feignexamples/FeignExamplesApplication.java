package com.nbenja.springcloud.feignexamples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

import feign.Feign;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@SpringBootApplication
public class FeignExamplesApplication {

  private static final String URL = "http://localhost:8080/customers/";

  public static void main(String[] args) {
    SpringApplication.run(FeignExamplesApplication.class, args);

    CustomerClient customerClient = Feign.builder().decoder(new GsonDecoder()).encoder(new
        GsonEncoder())
        .requestInterceptor(System.out::println)
        .target
            (CustomerClient.class, URL);
    System.out.println("****** Find by Id *******");
    System.out.println(customerClient.getCustomer(1L));
    System.out.println("******  *******");
    System.out.println("****** Find All *******");
    customerClient.getCustomers().forEach(System.out::println);
    System.out.println("******  *******");
    System.out.println("****** Create Customer *******");
    customerClient.createCustomer(new Customer("Adam", "Ben"));
    System.out.println("******  *******");
  }

}

interface CustomerClient {

  @RequestLine("GET /{id}")
  Customer getCustomer(@Param("id") Long id);

  @RequestLine("GET /")
  List<Customer> getCustomers();

  @RequestLine("POST /")
  @Headers("Content-Type: application/json")
  void createCustomer(Customer customer);
}

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
class Customer {

  private Long id;
  private String firstName;
  private String lastName;

  Customer(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

}