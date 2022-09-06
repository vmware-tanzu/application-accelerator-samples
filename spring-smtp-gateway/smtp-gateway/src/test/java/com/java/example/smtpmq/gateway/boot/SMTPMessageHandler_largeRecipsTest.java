package com.java.example.smtpmq.gateway.boot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.List;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.net.smtp.SMTPClient;
import org.junit.jupiter.api.extension.ExtendWith;
import org.nhindirect.common.mail.SMTPMailMessage;
import org.nhindirect.common.mail.streams.SMTPMailMessageConverter;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.subethamail.smtp.server.SMTPServer;

@ExtendWith(SpringExtension.class)
public class SMTPMessageHandler_largeRecipsTest
{
    protected static MimeMessage sentMessage;
    
	@Test
	public void testMaxRecipientsExceeded_assertRecipientsLimited() throws Exception
	{	
		try (ConfigurableApplicationContext context = new SpringApplicationBuilder(
				TestChannelBinderConfiguration.getCompleteConfiguration(
						SmtpGatewayApplication.class))
				.run("")) 
		{
	        OutputDestination output = context.getBean(OutputDestination.class);
			final SMTPServer smtpServer = context.getBean(SMTPServer.class);
			if (!smtpServer.isRunning())
			{	
				smtpServer.start();
			}
			
	        final String sender = "sender@localhost";
	        	
	        final String body = "Subject: test\r\n\r\nTestmail";
	        final SMTPClient client = new SMTPClient();
	        client.connect("localhost", 1026);
	        client.helo("localhost");
	        client.setSender(sender);
	        for (int i = 0; i < 550; ++i)
	        	client.addRecipient("rcpt" + i + "@localhost.com");
	        
	        assertTrue(client.sendShortMessageData(body));
	        client.quit();
	        client.disconnect();
	        
	        final Message<?> msg  = output.receive();
	        
	        final SMTPMailMessage smtpMailMessage = SMTPMailMessageConverter.fromStreamMessage(msg);
	        
	        final List<InternetAddress> recips = smtpMailMessage.getRecipientAddresses();
	        assertEquals(550, recips.size());			
		}
		
	}
}
