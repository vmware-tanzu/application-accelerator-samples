package com.java.example.tanzu.wherefordinner.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

import com.java.example.tanzu.wherefordinner.publisher.EmailPublisher;
import com.java.example.tanzu.wherefordinner.publisher.LoggerPublisher;

@Configuration
public class PublisherConfiguration 
{
	@ConditionalOnProperty(name="where-for-dinner.notifications.email.to")
	@Bean
	public EmailPublisher emailPublisher(final JavaMailSender mailSender, 
			final EmailMessageConfigProperties props)
	{
		return new EmailPublisher(mailSender, props);
	}
	
	@ConditionalOnProperty(name="where-for-dinner.notifications.logger.enabled", havingValue="true")
	@Bean
	public LoggerPublisher loggerPublisher()
	{
		return new LoggerPublisher();
	}
}
