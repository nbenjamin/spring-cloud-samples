### Spring Cloud Consul for service discovery

This example shows how to register your service with consul and also discovery it.

#### Register your service with consul

```xml
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-consul-discovery</artifactId>
    </dependency>
```

Add the following annotation for registering your service with consul
```java
    @EnableDiscoveryClient
```

Go to your consul url  - [localhost:8500](localhost:8500)
under the service you can your service already registered with the above changes

