package com.nbenja.springcloud.resilience4jexample.config;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;


@Configuration
public class Resilience4JConfiguration {


    @Bean
    public Customizer<Resilience4JCircuitBreakerFactory> slowCustomizer() {

        return factory -> {
            factory.configure(
                    builder -> builder
                            .circuitBreakerConfig(circuitBreakerConfig())
                            .timeLimiterConfig(TimeLimiterConfig.custom()
                                    .timeoutDuration(Duration.ofSeconds(1)).build()),
                    "breaker");
            factory.addCircuitBreakerCustomizer(circuitBreaker -> circuitBreaker.getEventPublisher()
            .onEvent( event -> {
                System.out.println("Event Type " + event.getEventType());
                System.out.println("Event Time " + event.getCreationTime());
            }));
        };
    }

    /**
     * In this configuration, it waits for the slidingWindow to open the circuit breaker and if the request
     * is continue to fail then its again checking for allowed number of calls in Half Open State
     * @return
     */
    @Bean
    public CircuitBreakerConfig circuitBreakerConfig() {
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                .failureRateThreshold(10)
                .waitDurationInOpenState(Duration.ofSeconds(10))
                .slowCallDurationThreshold(Duration.ofSeconds(1))
                .slidingWindow(10, 5, CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                .recordExceptions(RuntimeException.class)
                .writableStackTraceEnabled(true)
                .enableAutomaticTransitionFromOpenToHalfOpen()
                .permittedNumberOfCallsInHalfOpenState(2)
                .build();
        return circuitBreakerConfig;

    }

    @Bean
    public CircuitBreakerRegistry circuitBreakerRegistry() {
        return CircuitBreakerRegistry.of(circuitBreakerConfig());
    }


}
