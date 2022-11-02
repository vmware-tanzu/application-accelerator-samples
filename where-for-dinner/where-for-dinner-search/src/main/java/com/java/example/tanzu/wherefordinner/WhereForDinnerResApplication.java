package com.java.example.tanzu.wherefordinner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
@EnableR2dbcRepositories("com.java.example.tanzu.wherefordinner.repository")
public class WhereForDinnerResApplication {

	public static void main(String[] args) {
		SpringApplication.run(WhereForDinnerResApplication.class, args);
	}

}
