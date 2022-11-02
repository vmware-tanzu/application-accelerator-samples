package com.java.example.smtpmq.gateway.boot;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

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
@TestPropertySource("classpath:properties/testLocalNoAuthorizedCIDRConfig.properties")
@DirtiesContext
public class SafelistedServerSocket_noauthCidrTest
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
	public void testConnectionAuthorized_assertNotConnected() throws Exception
	{
		final Socket sock = new Socket("localhost", smtpServer.getPort());
		// give the server 1 second to terminate the connection before tying to communicate
		Thread.sleep(2000);
		
		// say hello
		final PrintWriter out =
		        new PrintWriter(sock.getOutputStream(), true);
		out.write("ehlo cerner.com");
		out.flush();
		
		/* attempts to read something from the socket
		 * it should be expected the socket will result in a socket reset exception
		 */
		int i = 0;
		boolean exceptionOccured = false;
		try
		{
			final BufferedReader input = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			i = input.read();
			sock.close();
		}
		catch (Exception e)
		{
			exceptionOccured = true;
		}
		
		assertTrue(i == -1 || exceptionOccured);
	}
}
