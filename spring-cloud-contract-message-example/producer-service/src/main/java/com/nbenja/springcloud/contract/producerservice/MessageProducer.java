package com.nbenja.springcloud.contract.producerservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {

    @Autowired
    private Source source;

    public void publish(User user) {

        Message<User> userMessage =  MessageBuilder.withPayload(user).build();
        source.output().send(userMessage);

    }
}
