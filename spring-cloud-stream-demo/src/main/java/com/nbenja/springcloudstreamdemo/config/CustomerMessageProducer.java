package com.nbenja.springcloudstreamdemo.config;


import com.nbenja.springcloudstreamdemo.domain.Customer;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;

@EnableBinding(Source.class)
public class CustomerMessageProducer {

    private Source source;

    public CustomerMessageProducer(Source source) {
        this.source = source;
    }

    public boolean addNewCustomers(Customer customer){
        return source.output().send(MessageBuilder.withPayload(customer).build());
    }

}
