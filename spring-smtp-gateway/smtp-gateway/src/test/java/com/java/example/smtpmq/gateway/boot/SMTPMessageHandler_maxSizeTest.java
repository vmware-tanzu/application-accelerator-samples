package com.java.example.smtpmq.gateway.boot;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.apache.commons.net.smtp.SMTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.subethamail.smtp.server.SMTPServer;

@WebAppConfiguration 
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {SmtpGatewayApplication.class}) 
@Configuration
@TestPropertySource("classpath:properties/testConfig.properties")
@DirtiesContext
public class SMTPMessageHandler_maxSizeTest
{
	@Autowired 
	protected SMTPServer smtpServer;
	
	@BeforeEach
	public void setUp()
	{ 
		if (!smtpServer.isRunning())
		{	
			smtpServer.start();
		}
	}
		

	@Test
	public void testMaxSizeExceeded_assertRejected() throws Exception
	{

        final String sender = "sender@localhost";

        	
        final StringBuilder builder = new StringBuilder("Message-ID: 12234\r\nSubject: test\r\n\r\nTestmail");
        for (int i = 0; i < 2000; ++i)
        	builder.append("A");
        
        
        final SMTPClient client = new SMTPClient();
        client.connect("localhost", 1026);
        client.helo("localhost");
        client.setSender(sender);
        client.addRecipient("rcpt@localhost.com");
        
        assertFalse(client.sendShortMessageData(builder.toString()));
        client.quit();
        client.disconnect();

	}
	
	@Test
	public void testMaxHeaderSizeExceeded_assertRejected() throws Exception
	{
        final String sender = "sender@localhost";

        
        final StringBuilder data = new StringBuilder("To: ");
        for (int i = 1; i <= 5000; ++i)
        {
        	data.append("rcpt" + i + "@localhost.com");
        	if (i < 5000)
        		data.append(", ");
        }
        	
        data.append("\r\nSubject: test\r\n\r\nTestmail");        
        
        final SMTPClient client = new SMTPClient();
        client.connect("localhost", 1026);
        client.helo("localhost");
        client.setSender(sender);
        client.addRecipient("rcpt@localhost.com");
        
        assertFalse(client.sendShortMessageData(data.toString()));
        client.quit();
        client.disconnect();
	}	
}
