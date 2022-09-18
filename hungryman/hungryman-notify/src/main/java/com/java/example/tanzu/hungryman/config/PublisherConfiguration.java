package com.java.example.tanzu.hungryman.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

import com.java.example.tanzu.hungryman.publisher.EmailPublisher;
import com.java.example.tanzu.hungryman.publisher.LoggerPublisher;

@Configuration
public class PublisherConfiguration 
{
	@ConditionalOnProperty(name="hungryman.notifications.email.to")
	@Bean
	public EmailPublisher emailPublisher(final JavaMailSender mailSender, 
			final EmailMessageConfigProperties props)
	{
		return new EmailPublisher(mailSender, props);
	}
	
	@ConditionalOnProperty(name="hungryman.notifications.logger.enabled", havingValue="true")
	@Bean
	public LoggerPublisher loggerPublisher()
	{
		return new LoggerPublisher();
	}
}
