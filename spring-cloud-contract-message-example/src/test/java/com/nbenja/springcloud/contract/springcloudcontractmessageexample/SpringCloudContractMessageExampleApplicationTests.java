package com.nbenja.springcloud.contract.springcloudcontractmessageexample;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.StubTrigger;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringCloudContractMessageExampleApplication.class)
@AutoConfigureStubRunner(workOffline = true, ids = "com.nbenja.springcloud.contract:producer-service")
public class SpringCloudContractMessageExampleApplicationTests {

	@Autowired
	private MessageListener messageListener;

	@Autowired
	private Sink sink;

	@Autowired
	private StubTrigger stubTrigger;

	//@Test
	public void messageListener_streamTestInMemory_pusblishAndConsumeSuccessfully() {
		Message<User> userMessage =MessageBuilder.withPayload(new User(1L, "Adam", "Benjamin",
				"adamBen@gmail.com"))
				.build();
		SubscribableChannel subscribableChannel = sink.input();
		subscribableChannel.send(userMessage);

		assertThat(messageListener.getUsers().size(), is(equalTo(1)));
		assertThat(messageListener.getUsers().get(0).getFirstName(), is(equalTo("Adam")));
	}

	@Test
	public void messageListener_streamTestInMemoryIntegrationTest_pusblishAndConsumeSuccessfully() {
		User userMessage = new User(1L, "Adam", "Benjamin",
				"adamBen@gmail.com");

		stubTrigger.trigger("user-message");


		assertThat(messageListener.getUsers().size(), is(equalTo(1)));
		assertThat(messageListener.getUsers().get(0).getFirstName(), is(equalTo("Adam")));
	}

}
