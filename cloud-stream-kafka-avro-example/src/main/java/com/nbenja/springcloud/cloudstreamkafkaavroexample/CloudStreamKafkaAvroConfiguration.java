package com.nbenja.springcloud.cloudstreamkafkaavroexample;

import com.nbenja.springcloud.cloudstreamkafkaavroexample.adapter.message.CustomSinkSource;
import com.nbenja.springcloud.cloudstreamkafkaavroexample.avro.domain.Request;
import com.nbenja.springcloud.cloudstreamkafkaavroexample.utils.RequestSerialization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.stream.schema.avro.AvroSchemaMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.support.MessageBuilder;


@Configuration
public class CloudStreamKafkaAvroConfiguration {

/*
    @Bean
    public MessageConverter messageConverter(){
//        AvroSchemaMessageConverter messageConverter =  new AvroSchemaMessageConverter(MimeType
//                .valueOf("avro/bytes"));
//        messageConverter.setSchemaLocation(new ClassPathResource("schema/Message.avsc"));
        AvroSchemaMessageConverter messageConverter =  new AvroSchemaMessageConverter();
        messageConverter.setSchemaLocation(new ClassPathResource("schema/Message.avsc"));
        return messageConverter;
    }
*/


    @Bean
    public MessageConverter avroMessageConverter() {
        return new AvroSchemaMessageConverter();
    }

    @Autowired
    CustomSinkSource customSinkSource;

    @Bean
    CommandLineRunner commandLineRunner() {
        return (args) -> {

            //Request testMessage = Request.newBuilder().setRequestId("test-id").setData
            //        ("test-message").build();
            Request testMessage = new Request("t", "test");
            byte [] payload = new RequestSerialization().requestSerialize(testMessage);
            Message message = MessageBuilder.withPayload(payload).setHeader("contentType", "application/avro")
                    .build();
            customSinkSource.inputChannel().send(message);
        };

    }
}
