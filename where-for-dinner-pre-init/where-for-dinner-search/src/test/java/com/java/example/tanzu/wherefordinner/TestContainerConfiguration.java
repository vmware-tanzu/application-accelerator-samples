package com.java.example.tanzu.wherefordinner;

import org.springframework.boot.devtools.restart.RestartScope;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.RabbitMQContainer;

@TestConfiguration(proxyBeanMethods = false)
public class TestContainerConfiguration 
{
	@RestartScope
    @ServiceConnection
    @Bean
    public RabbitMQContainer rabbitMQContainer() {
        return new RabbitMQContainer("rabbitmq:3.7.25-management-alpine");
    }
    
    @Profile("mysql")
	@RestartScope
    @ServiceConnection
    @Bean
    public MariaDBContainer<?> mySQLContainer() {
        return new MariaDBContainer<>("mariadb:10.3.6");
        
    }
    
    @Profile("postgres")
	@RestartScope
    @ServiceConnection
    @Bean
    public PostgreSQLContainer<?> postgresContainer() {
        return new PostgreSQLContainer<>("postgres:9.6.12");
        
    }
}
