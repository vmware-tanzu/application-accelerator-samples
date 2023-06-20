package com.java.example.smtpsink.functions;

import java.io.ByteArrayOutputStream;
import java.util.function.Function;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Configuration
public class SMTPMessageSink 
{
	private static Logger log = LogManager.getLogger(SMTPMessageSink.class);
	
	@Bean
	public Function<Flux<Message<?>>, Mono<Void> >  processSMTPMessage()
	{
		return msgs -> msgs.map(msg -> 
		{
			log.info("Received SMTP messages from queue.");
			
			var retVal = "";
			
			final var smtpMessage = SMTPMailMessageConverter.fromStreamMessage(msg);
			
			try(ByteArrayOutputStream oStream = new ByteArrayOutputStream())
			{
				smtpMessage.getMimeMessage().writeTo(oStream);
				oStream.flush();
				
				retVal = oStream.toString("ASCII");
			} 
			catch (Exception e) 
			{
				log.error("Error translating SMTP message to string: {}", e.getMessage(), e);
			}
			
			return retVal;
		})
		.doOnNext(System.out::println)
		.then();
	}
}
