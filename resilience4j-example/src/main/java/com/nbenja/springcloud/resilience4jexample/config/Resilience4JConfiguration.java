package com.nbenja.springcloud.resilience4jexample.config;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;


@Configuration
public class Resilience4JConfiguration {


    @Bean
    public Customizer<Resilience4JCircuitBreakerFactory> slowCustomizer() {
        return factory -> factory.configure(
                builder -> builder
                        .circuitBreakerConfig(circuitBreakerConfig())
                        .timeLimiterConfig(TimeLimiterConfig.custom()
                                .timeoutDuration(Duration.ofSeconds(1)).build()),
                "breaker");
    }


    @Bean
    public CircuitBreakerConfig circuitBreakerConfig() {
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                .failureRateThreshold(10)
                .waitDurationInOpenState(Duration.ofSeconds(120))
                .slowCallDurationThreshold(Duration.ofSeconds(1))
                .slidingWindow(3, 5, CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                //.waitDurationInOpenState(Duration.ofMinutes(1))
                //.permittedNumberOfCallsInHalfOpenState(2)
                .recordExceptions(RuntimeException.class)
                .build();
        return circuitBreakerConfig;

    }

    @Bean
    public CircuitBreaker circuitBreaker() {
        CircuitBreakerRegistry registry = CircuitBreakerRegistry.of(circuitBreakerConfig());
        return registry.circuitBreaker("CBExample");
    }



}
