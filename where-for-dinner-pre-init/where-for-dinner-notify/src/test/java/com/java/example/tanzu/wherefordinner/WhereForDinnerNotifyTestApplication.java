package com.java.example.tanzu.wherefordinner;

import org.springframework.boot.SpringApplication;

public class WhereForDinnerNotifyTestApplication 
{
    public static void main(String[] args) 
    {
        SpringApplication.from(WhereForDinnerNotifyApplication::main).with(TestContainerConfiguration.class).run(args);
    }
}
