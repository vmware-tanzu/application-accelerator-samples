package com.java.example.tanzu.wherefordinner.publisher;

import java.text.DateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import jakarta.mail.Session;
import jakarta.mail.Message.RecipientType;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;

import com.java.example.tanzu.wherefordinner.config.EmailMessageConfigProperties;
import com.java.example.tanzu.wherefordinner.model.Availability;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class EmailPublisher implements Publisher
{
	protected final JavaMailSender emailSender;
	
	protected final String from;
	
	protected final List<String> to;
	
	protected final String subject;
	
	public EmailPublisher(JavaMailSender emailSender, EmailMessageConfigProperties props)
	{
		this.emailSender = emailSender;
		
		this.from = props.getFrom();
		
		this.to = props.getTo();
		
		this.subject = props.getSubject();
	}
	
	
	@Override
	public Mono<Void> publishAvailability(Availability avail) 
	{
		final MimeMessage message = new MimeMessage((Session)null); 
		
		try
		{
			final var msgBuilder = new StringBuilder("Updated dining availability for ").append(avail.getDiningName());
			if (avail.getAvailabilityWindows().isEmpty())
				msgBuilder.append("\tNo available dining times");
			else
			{
				int cnt = 1;
				for (Availability.AvailabilityWindow window : avail.getAvailabilityWindows())
				{
					final var formatter = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, Locale.getDefault());
					
					
					final var startTime = formatter.format(Date.from(Instant.ofEpochMilli(window.getStartTime())));
					final var endTime = formatter.format(Date.from(Instant.ofEpochMilli(window.getEndTime())));
					
					msgBuilder.append("\r\n\t").append(cnt).append(". Window Start Time: ").append(startTime);
					msgBuilder.append("\r\n\t").append(cnt).append(". Window End Time: ").append(endTime).append("\r\n");
					
					++cnt;
				}
			}	
			
			message.setFrom(from);
			message.setSubject(subject);
			message.setText(message.toString());
			
			for (String toRecip : to)
				message.addRecipient(RecipientType.TO, new InternetAddress(toRecip));
			
			emailSender.send(message);
		}
		catch (Exception e)
		{
			log.error("Failed to send email notification.", e);
		}
		
		return Mono.empty();
	}

}
