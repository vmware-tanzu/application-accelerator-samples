package com.java.example.tanzu.hungryman;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import reactivefeign.spring.config.EnableReactiveFeignClients;

@SpringBootApplication
@EnableReactiveFeignClients
@EnableDiscoveryClient
public class HungrymanSearchProcApplication {

	public static void main(String[] args) 
	{
		new SpringApplicationBuilder(HungrymanSearchProcApplication.class)
		 .run(args);
	}

}
