package com.nbenja.springcloud.resilience4jexample.controller;


import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class StudentController {


    private CircuitBreakerFactory circuitBreakerFactory;

    private Response successResponse = new Response("Success Response");
    private Response fallbackResponse = new Response("Fallback Response");

    public StudentController(CircuitBreakerFactory circuitBreakerFactory) {
        this.circuitBreakerFactory = circuitBreakerFactory;
    }

    @GetMapping("/test/{delay}")
    public Response resilience4J(@PathVariable("delay") int delay) {

        return circuitBreakerFactory.create("breaker").run(
                () -> this.randomSlow(delay), t -> this.resilience4JFallbackMethod()
        );
    }

    private Response randomSlow(int num) {
        System.out.println("Random Slow method");
        try {
            TimeUnit.SECONDS.sleep(num);
        } catch (InterruptedException e) {
        }

        return successResponse;
    }

    private Response resilience4JFallbackMethod() {
        System.out.println("Fallback method");
        return fallbackResponse;
    }

}

class Response {

    private String message;

    public Response(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}