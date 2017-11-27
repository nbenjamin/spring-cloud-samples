package com.nbenja.springcloud.cloudstreamkafkaavroexample.adapter.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 *
 */
public interface CustomSinkSource {

    String INPUT = "request";
    String OUTPUT = "response";

    @Input(INPUT)
    SubscribableChannel inputChannel();

    @Output(OUTPUT)
    MessageChannel outputChannel();
}
