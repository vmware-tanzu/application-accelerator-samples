package com.java.example.tanzu.hungryman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
@EnableR2dbcRepositories("com.java.example.tanzu.hungryman.repository")
public class HungrymanResApplication {

	public static void main(String[] args) {
		SpringApplication.run(HungrymanResApplication.class, args);
	}

}
