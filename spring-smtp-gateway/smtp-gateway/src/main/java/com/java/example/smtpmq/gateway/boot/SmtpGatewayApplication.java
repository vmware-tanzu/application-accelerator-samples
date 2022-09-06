package com.java.example.smtpmq.gateway.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.subethamail.smtp.server.SMTPServer;

@ComponentScan({"com.java.example.smtpmq.gateway"})
@SpringBootApplication
@EnableScheduling
public class SmtpGatewayApplication implements CommandLineRunner
{
	@Autowired 
	protected SMTPServer smtpServer;
	
	public static void main(String[] args) 
	{
		new SpringApplicationBuilder(SmtpGatewayApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);		
    }	
	
	@Override
	public void run(String... args) throws Exception 
	{
	    smtpServer.start();
	}	
}
