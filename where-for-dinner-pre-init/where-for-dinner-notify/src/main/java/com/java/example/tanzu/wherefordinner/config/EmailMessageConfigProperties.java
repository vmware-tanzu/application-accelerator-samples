package com.java.example.tanzu.wherefordinner.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * Configuration properties for the email sender, a comma delimited set of recipients, and a message subject.  Java mail
 * server information is configured using the standard Spring spring.mail.* configuration parameters.
 * @author Greg Meyer
 *
 */
@Configuration
@ConfigurationProperties(prefix = "where-for-dinner.notifications.email")
@Data
public class EmailMessageConfigProperties 
{
	private boolean enabled;
	
	private String from;
	
	private List<String> to;
	
	private String subject;
}
