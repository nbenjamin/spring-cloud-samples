package com.nbenja.springcloud.contract.producerservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProducerServiceApplication.class)
@AutoConfigureMessageVerifier
public class MessageBaseClassTest {

	@Autowired
	private MessageProducer messageProducer;

	public void publishMessage() {
		System.out.println(" ******** " + messageProducer);
		messageProducer.publish(new User(1L, "Adam", "Benjamin", "adamBen@gmail.com"));
	}

	@Test
	public void before() {

	}
}
