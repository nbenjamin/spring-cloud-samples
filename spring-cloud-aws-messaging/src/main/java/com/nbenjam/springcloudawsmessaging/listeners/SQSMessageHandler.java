package com.nbenjam.springcloudawsmessaging.listeners;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.nbenjam.springcloudawsmessaging.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
public class SQSMessageHandler {

    private QueueMessagingTemplate queueMessagingTemplate;

    @Autowired
    public SQSMessageHandler(AmazonSQSAsync amazonSQSAsync) {
        this.queueMessagingTemplate = new QueueMessagingTemplate(amazonSQSAsync);
    }

    @SqsListener("demo")
    public void processMessage(String message) {
        System.out.println("Message Received - " + message);
    }
}
