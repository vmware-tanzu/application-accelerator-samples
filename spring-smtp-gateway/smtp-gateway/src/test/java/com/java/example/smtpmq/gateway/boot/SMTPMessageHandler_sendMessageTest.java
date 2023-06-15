package com.java.example.smtpmq.gateway.boot;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.nio.charset.Charset;
import java.util.List;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.smtp.SMTPClient;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.subethamail.smtp.server.SMTPServer;

import com.java.example.smtpmq.gateway.server.SMTPMailMessage;
import com.java.example.smtpmq.gateway.streams.SMTPMailMessageConverter;

@ExtendWith(SpringExtension.class)
@TestPropertySource("classpath:properties/testConfig.properties")
public class SMTPMessageHandler_sendMessageTest
{
    protected static MimeMessage sentMessage;
    
	@Test
	public void testGoodMessage_assertMessageSent() throws Exception
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

        	
	        final String data = "To: rcpt@localhost.com\r\nSubject: test\r\n\r\nTestmail";
	        final SMTPClient client = new SMTPClient();
	        client.connect("localhost", 1026);
	        client.helo("localhost");
	        client.setSender(sender);
	        for (int i = 0; i < 4; ++i)
	        	client.addRecipient("rcpt" + i + "@localhost.com");
	        
	        assertTrue(client.sendShortMessageData(data));
	        client.quit();
	        client.disconnect();

	        final Message<?> msg  = output.receive();
	        
	        final SMTPMailMessage smtpMailMessage = SMTPMailMessageConverter.fromStreamMessage(msg);
	        
	        sentMessage = smtpMailMessage.getMimeMessage();
	        
	        assertEquals("test", sentMessage.getSubject());
	        assertEquals("rcpt@localhost.com", sentMessage.getHeader("To")[0]);
	        final String content = IOUtils.toString(sentMessage.getInputStream(), Charset.defaultCharset());
	        assertEquals("Testmail\r\n", content);
	        
	        final List<InternetAddress> recips = smtpMailMessage.getRecipientAddresses();
	        
	        assertEquals(4, recips.size());			
		};
	}
	
	@Test
	public void testGoodMessageEmptyFrom_assertMessageSent() throws Exception
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
			
	        final String sender = "";

        	
	        final String data = "To: rcpt@localhost.com\r\nSubject: test\r\n\r\nTestmail";
	        final SMTPClient client = new SMTPClient();
	        client.connect("localhost", 1026);
	        client.helo("localhost");
	        client.setSender(sender);
	        for (int i = 0; i < 4; ++i)
	        	client.addRecipient("rcpt" + i + "@localhost.com");
	        
	        assertTrue(client.sendShortMessageData(data));
	        client.quit();
	        client.disconnect();
	        
	        final Message<?> msg  = output.receive();
	        
	        final SMTPMailMessage smtpMailMessage = SMTPMailMessageConverter.fromStreamMessage(msg);
	        
	        assertNull(smtpMailMessage.getMailFrom());
	        sentMessage = smtpMailMessage.getMimeMessage();
	        
	        assertEquals("test", sentMessage.getSubject());
	        assertEquals("rcpt@localhost.com", sentMessage.getHeader("To")[0]);
	        final String content = IOUtils.toString(sentMessage.getInputStream(), Charset.defaultCharset());
	        assertEquals("Testmail\r\n", content);
	        
	        final List<InternetAddress> recips = smtpMailMessage.getRecipientAddresses();
	        
	        assertEquals(4, recips.size());			
			
		}

	}	
	
	@Test
	public void testGoodInvalidFromMessage_assertMessageNotSent() throws Exception
	{
		try (ConfigurableApplicationContext context = new SpringApplicationBuilder(
				TestChannelBinderConfiguration.getCompleteConfiguration(
						SmtpGatewayApplication.class))
				.run("")) 
		{
			final SMTPServer smtpServer = context.getBean(SMTPServer.class);
			if (!smtpServer.isRunning())
			{	
				smtpServer.start();
			}
			
	        final String sender = "bl#@#$#.Localhost.com";

        	
	        final String data = "To: rcpt@localhost.com\r\nSubject: test\r\n\r\nTestmail";
	        final SMTPClient client = new SMTPClient();
	        client.connect("localhost", 1026);
	        client.helo("localhost");
	        client.setSender(sender);
	        for (int i = 0; i < 4; ++i)
	        	client.addRecipient("rcpt" + i + "@localhost.com");
	        
	        assertFalse(client.sendShortMessageData(data));
	        client.quit();
	        client.disconnect();
		}
		

        
 	}	

}
