package com.nbenjam.springcloudawsmessaging.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import org.springframework.cloud.aws.autoconfigure.context.properties.AwsCredentialsProperties;
import org.springframework.cloud.aws.autoconfigure.context.properties.AwsRegionProperties;
import org.springframework.cloud.aws.context.config.annotation.EnableContextInstanceData;
import org.springframework.cloud.aws.messaging.config.QueueMessageHandlerFactory;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.PayloadMethodArgumentResolver;

import java.util.Collections;

@Configuration
@EnableContextInstanceData
public class AwsConfiguration {


    @Bean
    public QueueMessageHandlerFactory queueMessageHandlerFactory() {
        QueueMessageHandlerFactory factory = new QueueMessageHandlerFactory();
        MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();

        //set strict content type match to false
        messageConverter.setStrictContentTypeMatch(false);
        factory.setArgumentResolvers(Collections.singletonList(new PayloadMethodArgumentResolver(messageConverter)));
        return factory;
    }


    @Bean
    public NotificationMessagingTemplate notificationMessagingTemplate(AmazonSNS amazonSNS) {
        return new NotificationMessagingTemplate(amazonSNS);
    }

    @Bean
    @Primary
    public AmazonSQSAsync amazonSQSAsync(AwsCredentialsProperties awsCredentialsProperties, AwsRegionProperties awsRegionProperties) {

        return AmazonSQSAsyncClientBuilder.standard().withRegion(awsRegionProperties.getStatic())
                .withCredentials(new AWSStaticCredentialsProvider(
                        new BasicAWSCredentials(awsCredentialsProperties.getAccessKey(),
                                awsCredentialsProperties.getSecretKey()))).build();

    }

    @Bean
    @Primary
    public AmazonSNS amazonSNS(AwsCredentialsProperties awsCredentialsProperties, AwsRegionProperties awsRegionProperties) {

        return AmazonSNSClientBuilder.standard().withRegion(awsRegionProperties.getStatic())
                .withCredentials(new AWSStaticCredentialsProvider(
                        new BasicAWSCredentials(awsCredentialsProperties.getAccessKey(),
                                awsCredentialsProperties.getSecretKey()))).build();

    }
}
