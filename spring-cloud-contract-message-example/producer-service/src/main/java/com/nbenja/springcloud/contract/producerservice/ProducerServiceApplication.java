package com.nbenja.springcloud.contract.producerservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableBinding(Source.class)
public class ProducerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProducerServiceApplication.class, args);
	}
}

@RestController
class MessageProducerController {

	@Autowired
	private MessageProducer messageProducer;

	@PostMapping("messages")
	public void publishMessage(User user) {
		// Adding User manually to test StubTrigger
		if(user == null) {
			user = new User(1L, "Adam", "Benjamin",
					"adamBen@gmail.com");
		}
		messageProducer.publish(user);
	}
}