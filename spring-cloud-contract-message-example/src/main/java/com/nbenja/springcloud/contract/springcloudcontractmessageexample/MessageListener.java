package com.nbenja.springcloud.contract.springcloudcontractmessageexample;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Getter
public class MessageListener {

    List<User> users = new ArrayList<>();

    @StreamListener(Sink.INPUT)
    public void handleUserMessage(User user) {
        log.info("User message received {}", user);
        users.add(user);
    }
}
