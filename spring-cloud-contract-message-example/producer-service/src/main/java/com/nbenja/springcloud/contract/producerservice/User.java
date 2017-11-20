package com.nbenja.springcloud.contract.producerservice;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable{
    private Long id;
    private String firstName;
    private String lastName;
    private String emailID;
}
