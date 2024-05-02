package com.java.example.tanzu.wherefordinner;

import org.springframework.boot.SpringApplication;

public class WhereForDinnerResTestApplication {
	
    public static void main(String[] args) 
    {
        SpringApplication.from(WhereForDinnerResApplication::main).with(TestContainerConfiguration.class).run(args);
    }
}
