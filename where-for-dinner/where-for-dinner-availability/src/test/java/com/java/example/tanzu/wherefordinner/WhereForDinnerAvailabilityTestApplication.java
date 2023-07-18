package com.java.example.tanzu.wherefordinner;

import org.springframework.boot.SpringApplication;

public class WhereForDinnerAvailabilityTestApplication 
{
    public static void main(String[] args) 
    {
        SpringApplication.from(WhereForDinnerAvailabilityApplication::main).with(TestContainerConfiguration.class).run(args);
    }
}
