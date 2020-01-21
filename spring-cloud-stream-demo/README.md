# Spring Cloud Stream demo

This example shows how to connect `Kafka` using `Spring Cloud Stream`

### Infrastructure
This will bring `Kafka`, `Zookeeper` and `MongoDB`
```bash
docker-compose up
```

### Test
This example has two endpoint, one for creating a customer and other one is for retrieving all the customers

using [HTTPie](https://httpie.org/) for testing

Create new Customer
```bash
http POST localhost:8088/customers <<< '{"name":"Test User", "age": 25}'
```

This endpoint publish new Customer Event to kafka, and the `StreamListener` consume the Customer object and save in to MongoDB

```java
@StreamListener(Source.OUTPUT)
public void createCustomers(Customer customer){
    this.customerRepository.save(customer).subscribe();
}
```

Retrieve all customer, you can find all the new customer in the below endpoint

```bash
http http://localhost:8088/customers --stream
```
This endpoint returns content-type as `text/event-stream` 
