package com.java.example.tanzu.wherefordinner;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.RabbitMQContainer;

@TestConfiguration(proxyBeanMethods = false)
public class TestContainerConfiguration 
{
    @Bean
    @ServiceConnection
    public RabbitMQContainer rabbitMQContainer() {
        return new RabbitMQContainer("rabbitmq:3.7.25-management-alpine");
    }
    
    @Profile("redis")
    @Bean
    @ServiceConnection(name="redis")
    public GenericContainer<?> redisContainer() {
        final var redisContainer = new GenericContainer<>("redis:7.0.12-alpine");
        redisContainer.addExposedPort(6379);
        
    	return redisContainer;
    }
}
