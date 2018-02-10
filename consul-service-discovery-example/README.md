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
#### Run consul locally 
Added `docker-compose.yml` with consul configuration. Use the following command to run the docker
 consul instance
 
 `
 cd docker
 docker-compose -f docker-compose.yml up
 `
Go to your consul url  - [localhost:8500](localhost:8500)
under the service you can your service already registered with the above changes

#### Service discovery

Autowire `DiscoveryClient` either directly or by constructor and create the 
`ServiceInstance` from `DiscoveryClient`
```java
    private DiscoveryClient discoveryClient;
    private ServiceInstance serviceInstance;

    public ConsulServiceController(DiscoveryClient discoveryClient) {
        this.serviceInstance = discoveryClient.getInstances("customer-service").stream()
                .findFirst().get();
    }
``` 

How to get the URI

`serviceInstance.getUri()`

#### Run application locally
First run `CustomerServiceApplication.java`, this will register the service with consul

Then run `ConsulServiceDiscoveryApplication` and hit the below url to test it

`http://localhost:8080/discover/`

