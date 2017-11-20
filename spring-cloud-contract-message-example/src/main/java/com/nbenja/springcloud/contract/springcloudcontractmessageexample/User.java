package com.nbenja.springcloud.contract.springcloudcontractmessageexample;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class User implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private String emailID;
}
