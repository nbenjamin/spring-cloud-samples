package com.nbenja.springcloud.springcloudvaultdemo.config;

import com.nbenja.springcloud.springcloudvaultdemo.domain.ApplicationProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoConfiguration {


    @Bean
    public CommandLineRunner commandLineRunner(ApplicationProperties applicationProperties) {
        return args -> {
            System.out.println(applicationProperties.getUsername());
            System.out.println(applicationProperties.getPassword());
        };
    }

}
