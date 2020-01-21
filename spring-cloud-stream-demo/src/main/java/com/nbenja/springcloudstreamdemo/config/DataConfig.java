package com.nbenja.springcloudstreamdemo.config;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import com.nbenja.springcloudstreamdemo.domain.Customer;
import com.nbenja.springcloudstreamdemo.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import reactor.core.publisher.Flux;

@Configuration
@EnableReactiveMongoRepositories(basePackages = "com.nbenja.springcloudstreamdemo.repository")
public class DataConfig extends AbstractReactiveMongoConfiguration {

    @Bean
    public CommandLineRunner commandLineRunner(CustomerRepository repository) {
        return args -> {
            repository
                    .deleteAll()
                    .thenMany(
                            Flux.just(new Customer(null,"Tom", 30),
                                    new Customer(null, "Ryan", 8),
                                    new Customer(null, "Adam", 6))
                                    .flatMap(repository::save)
                    )
                    .thenMany(repository.findAll())
                    .subscribe(c -> System.out.println("saving " + c.toString()));
        };
    }

    @Override
    public MongoClient reactiveMongoClient() {
        return MongoClients.create();
    }

    @Override
    protected String getDatabaseName() {
        return "customer";
    }
}
