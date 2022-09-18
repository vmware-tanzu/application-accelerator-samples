package com.example.helloapp;

import java.util.function.Function;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HelloAppApplication {

	@Bean
	public Function<String, String> hello() {
		return (in) -> {
			return "Hello " + in;
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(HelloAppApplication.class, args);
	}

}
