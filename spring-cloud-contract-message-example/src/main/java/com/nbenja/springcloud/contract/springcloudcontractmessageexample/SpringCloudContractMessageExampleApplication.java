package com.nbenja.springcloud.contract.springcloudcontractmessageexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(Sink.class)
@SpringBootApplication
public class SpringCloudContractMessageExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudContractMessageExampleApplication.class, args);
	}
}
