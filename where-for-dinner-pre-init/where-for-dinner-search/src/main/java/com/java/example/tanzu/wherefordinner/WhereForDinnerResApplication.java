package com.java.example.tanzu.wherefordinner;

import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.java.example.tanzu.wherefordinner.resources.SearchResource;

@SpringBootApplication
@RegisterReflectionForBinding(SearchResource.class)
public class WhereForDinnerResApplication {

	public static void main(String[] args) {
		SpringApplication.run(WhereForDinnerResApplication.class, args);
	}

}
