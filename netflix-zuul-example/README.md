### Spring cloud example with Netflix Zuul

#### Introduction
This example shows how to use [spring-cloud-netflix Zuul](https://cloud.spring.io/spring-cloud-netflix/single/spring-cloud-netflix.html#_router_and_filter_zuul).

In this example we have created two different services:

    1. customer-service
    2. account-service
    
and also created gateway service using Zuul proxy:

    1. zuul-gateway-service

`Zuul’s rule engine allows rules and filters to be written in essentially any JVM language,
 with built in support for Java and Groovy.`
 
 #### Dependency required for netflix-zuul
 
```xml
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-zuul</artifactId>
		</dependency>
```

Note : This example use spring boot version `1.5.8.RELEASE` and spring cloud version `Dalston.SR4`

##### zuul-gateway-service
 To enable Zuul, annotate a Spring Boot main class with @EnableZuulProxy, and this forwards local 
 calls to the appropriate service
 
```java

@EnableZuulProxy
@SpringBootApplication
public class ZuulGatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulGatewayServiceApplication.class, args);
	}
}

```

application yml changes

```yaml
zuul:
  routes:
    account:
      path: /accounts/**
      url: http://localhost:8082/accounts/
    customer:
      path: /customers/**
      url: http://localhost:8081/customers/
  prefix: /api

```

#### Testing

Please make sure you run all the services: 

    Test account service:
    
        http://localhost:8080/api/accounts/101
        http://localhost:8080/api/accounts/102
    
    Test customer service:
    
        http://localhost:8080/api/customers/200
        http://localhost:8080/api/customers/201
    
 
