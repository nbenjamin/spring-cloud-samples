package com.nbenja.springcloud.contract.producerservice;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String emailID;
}
