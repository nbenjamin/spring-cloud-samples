package com.nbenja.springcloud.consul.consulservicediscovery.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    private String id;
    private Long customerId;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public Customer(Long customerId, String firstName, String lastName, String phoneNumber) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }
}
