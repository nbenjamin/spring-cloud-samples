package com.nbenja.springcloud.springcloudcontracthttpexample;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.github.tomakehurst.wiremock.client.WireMock;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.junit.StubRunnerRule;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureWireMock(port = 8085)
@DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
public class SpringCloudContractHttpExampleApplicationTests {

	private RestTemplate restTemplate = restTemplate();

	@Rule
	public StubRunnerRule stubRunnerRule = new StubRunnerRule().downloadStub("com.nbenja" +
			".springcloud.scch","service").workOffline(true).withPort(8086);

	@Test
	public void userService_getUser_returnOk() {
		String json = "{\n" +
				"  \"id\": 1,\n" +
				"  \"firstName\": \"Ryan\",\n" +
				"  \"lastName\": \"Benjamin\",\n" +
				"  \"emailID\": \"ryanb@gmail.com\"\n" +
				"}";

		WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/user/1")).willReturn(WireMock
				.aResponse().withHeader("Content-Type", "application/json").withBody
						(json).withStatus(HttpStatus.OK.value())));
		ResponseEntity<User> userResponseEntity = restTemplate.getForEntity
				("http://localhost:8085/user/1", User.class);
		assertThat(userResponseEntity.getStatusCode(), is(equalTo(HttpStatus.OK)));
	}

	@Test
	public void userService_getUserIntegrationFromStub_returnOk() {
		ResponseEntity<User> userResponseEntity = restTemplate.getForEntity
				("http://localhost:8086/user/1", User.class);
		assertThat(userResponseEntity.getStatusCode(), is(equalTo(HttpStatus.OK)));
	}


	public RestTemplate restTemplate() {
		final RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(jacksonSupportsMoreTypes());
		return restTemplate;
	}


	private HttpMessageConverter jacksonSupportsMoreTypes() {//eg. Gitlab returns JSON as plain text
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
		return converter;
	}
}
