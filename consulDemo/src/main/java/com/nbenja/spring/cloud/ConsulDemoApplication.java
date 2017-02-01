package com.nbenja.spring.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Getter;
import lombok.Setter;

@EnableDiscoveryClient
@EnableConfigurationProperties
@RestController
@SpringBootApplication
public class ConsulDemoApplication {

	@Autowired
	private ApplicationSettings applicationSettings;

	@RequestMapping("/")
	public String home() {
		return "Hello, " + applicationSettings.getMessage();
	}

	public static void main(String[] args) {
		SpringApplication.run(ConsulDemoApplication.class, args);
	}
}

@Configuration
@ConfigurationProperties
class ApplicationSettings {

	@Getter
	@Setter
	private String message;

}