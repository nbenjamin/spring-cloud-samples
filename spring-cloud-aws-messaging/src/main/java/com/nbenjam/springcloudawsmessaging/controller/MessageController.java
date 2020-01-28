package com.nbenjam.springcloudawsmessaging.controller;

import com.nbenjam.springcloudawsmessaging.domain.Person;
import com.nbenjam.springcloudawsmessaging.listeners.SNSMessageHandler;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    private SNSMessageHandler snsMessageHandler;

    public MessageController(SNSMessageHandler snsMessageHandler) {
        this.snsMessageHandler = snsMessageHandler;
    }

    @PostMapping("/messages")
    @ResponseStatus(HttpStatus.CREATED)
    public void message(@RequestBody String message) {
        this.snsMessageHandler.publishMessage(message);
    }
}
