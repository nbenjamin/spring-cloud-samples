package com.nbenjam.springcloudawsmessaging.listeners;

import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SNSMessageHandler {

    private NotificationMessagingTemplate notificationMessagingTemplate;

    public SNSMessageHandler(NotificationMessagingTemplate notificationMessagingTemplate) {
        this.notificationMessagingTemplate = notificationMessagingTemplate;
    }

    public void publishMessage(String message) {
        Map<String, Object> headers = new HashMap<>();
        headers.put("subject", "PERSON_CREATED");
        MessageHeaders messageHeaders = new MessageHeaders(headers);
        this.notificationMessagingTemplate.send("demo-topic", MessageBuilder.createMessage(message, messageHeaders));
    }
}
