package com.nbenja.springcloud.consul.consulservicediscovery.adapter.api;

import com.nbenja.springcloud.consul.consulservicediscovery.domain.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/discover/")
public class ConsulServiceController {

    @Autowired
    private RestTemplate restTemplate;

    private DiscoveryClient discoveryClient;
    private ServiceInstance serviceInstance;

    public ConsulServiceController(DiscoveryClient discoveryClient) {
        this.serviceInstance = discoveryClient.getInstances("customer-service").stream()
                .findFirst().get();
        System.out.println(serviceInstance.getHost());
    }

    @GetMapping
    public List<Customer> getCustomers() {
        ResponseEntity<Customer[]> responseEntity = restTemplate.getForEntity
                (serviceInstance.getUri()+"/customers",Customer[].class);
        return Arrays.asList(responseEntity.getBody());
    }



}
