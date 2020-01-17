package com.nbenja.springcloud.springcloudvaultdemo;

import com.nbenja.springcloud.springcloudvaultdemo.domain.ApplicationProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties.class)
public class SpringCloudVaultDemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringCloudVaultDemoApplication.class, args);
	}
}
