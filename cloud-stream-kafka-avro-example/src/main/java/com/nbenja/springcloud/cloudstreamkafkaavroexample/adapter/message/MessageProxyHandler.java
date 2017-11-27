package com.nbenja.springcloud.cloudstreamkafkaavroexample.adapter.message;


import com.nbenja.springcloud.cloudstreamkafkaavroexample.avro.domain.Request;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;

import lombok.extern.slf4j.Slf4j;

@EnableBinding(CustomSinkSource.class)
@Slf4j
public class MessageProxyHandler {

    @StreamListener(CustomSinkSource.INPUT)
    @SendTo(CustomSinkSource.OUTPUT)
    public Message<Request> messageBridge(Message<Request> message) {
        log.info("Message payload received {}", message.getPayload());
        log.info("Message header received {}", message.getHeaders());
        return message;
    }
}
