package com.java.example.tanzu.wherefordinner;

import org.springframework.boot.SpringApplication;


public class WhereForDinnerSearchProcTestApplication 
{
    public static void main(String[] args) 
    {
        SpringApplication.from(WhereForDinnerSearchProcApplication::main).with(TestContainerConfiguration.class).run(args);
    }
}
