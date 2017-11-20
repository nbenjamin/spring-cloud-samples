package com.nbenja.springcloud.scch.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceController {

    @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<User> getUser(@PathVariable(name = "id") Long id) {
        User user = new User(1L, "Ryan", "Benjamin", "ryanb@gmail.com");
        return ResponseEntity.status(HttpStatus.OK.value()).body(user);
    }
}
