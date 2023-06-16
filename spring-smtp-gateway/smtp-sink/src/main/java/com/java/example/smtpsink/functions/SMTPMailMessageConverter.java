package com.java.example.smtpsink.functions;

import static org.springframework.messaging.support.MessageBuilder.createMessage;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Stream;

import jakarta.mail.Address;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import org.apache.commons.io.IOUtils;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;


/**
 * SpringCloud streams converter class for SMTPMailMessage objects.
 * @author Greg Meyer
 */
public class SMTPMailMessageConverter
{
	public static final String MESSAGE_ID_HEADER_NAME = "mail-message-id";
	public static final String MAIL_FROM_HEADER_NAME = "mail-from";	
	public static final String MAIL_RECIPIENTS_HEADER = "mail-recipients";	
	
	/**
	 * Converts an SMTPMailMessage object to a Spring Cloud Stream message.
	 * @param msg The mail message to convert.
	 * @return A SpringCloud streams message.
	 */
	public static Message<?> toStreamMessage(SMTPMailMessage msg)
	{
		return toStreamMessage(msg, null);
	}
		
	/**
	 * Converts an SMTPMailMessage object and custom headers to a Spring Cloud Stream message.
	 * @param msg The mail message to convert.
	 * @param hdrs Custom headers to add to the message.
	 * @return A SpringCloud streams message.
	 */
	public static Message<?> toStreamMessage(SMTPMailMessage msg, Map<String, Object> hdrs)
	{
	    final ByteArrayOutputStream out = new ByteArrayOutputStream();
	    final Map<String, Object> headerMap = new HashMap<>();
	    try
		{
	    	if (hdrs != null)
	    		hdrs.forEach((key, value) -> headerMap.put(key, value));
	    	
	    	msg.getMimeMessage().writeTo(out);
	    	
		    headerMap.put(MESSAGE_ID_HEADER_NAME, msg.getMimeMessage().getMessageID());
		    if (msg.getMailFrom() != null)
		    	headerMap.put(MAIL_FROM_HEADER_NAME, msg.getMailFrom().toString());
		    
		    if (!CollectionUtils.isEmpty(msg.getRecipientAddresses())) 
		    {

		        final Address[] distinctRecipientAddresses = getDistinctAddresses(msg.getRecipientAddresses());
		        final String headerValue = InternetAddress.toString(distinctRecipientAddresses);
		        headerMap.put(MAIL_RECIPIENTS_HEADER, headerValue);
		    }	    	
		} 
	    catch (IOException | MessagingException e)
		{
	    	throw new org.springframework.messaging.MessagingException("Failed to convert message to internal structure", e);
		}
	    	
	    final MessageHeaders headers = new MessageHeaders(headerMap);
		
	    return createMessage(out.toByteArray(), headers);
	}
	
	/**
	 * Converts a SpringCloud message to an SMTPMailMessage
	 * @param msg The message to convert.
	 * @return A SMTPMailMessage object.
	 */
	public static SMTPMailMessage fromStreamMessage(Message<?> msg)
	{
		final Object payload = msg.getPayload();
		
		if (!(payload instanceof String) && !(payload instanceof byte[]))
			return null;
		

					
		try (final InputStream inStream = (payload instanceof String)
				? IOUtils.toInputStream(String.class.cast(payload), Charset.defaultCharset())
				: new ByteArrayInputStream(byte[].class.cast(payload)))
		{
			final MimeMessage mimeMessage = new MimeMessage(Session.getDefaultInstance(new Properties()), inStream);
			
			final MessageHeaders headers = msg.getHeaders();
			final InternetAddress mailFrom = (headers.get(MAIL_FROM_HEADER_NAME) == null ) ? null : 
				new InternetAddress((String)headers.get(MAIL_FROM_HEADER_NAME));
			
			final String rcpts = (String)headers.get(MAIL_RECIPIENTS_HEADER);
			
			final InternetAddress fromAddr = mailFrom;
			final List<InternetAddress> rcptsTos = (!StringUtils.hasText(rcpts)) ? Collections.emptyList() : Arrays.asList(InternetAddress.parse(rcpts));
			
			return new SMTPMailMessage(mimeMessage, rcptsTos, fromAddr);
			
		}
		catch (MessagingException | IOException e)
		{
			throw new org.springframework.messaging.MessagingException("Failed to convert message from internal structure", e);
		}
	}
	
	protected static InternetAddress[] getDistinctAddresses(List<InternetAddress> addresses) 
	{
	   if (CollectionUtils.isEmpty(addresses)) 
	   {
		   return new InternetAddress[] {};
	   }

	   final Stream<String> distinctRecipients = addresses.stream()
		                                              .filter(address -> address instanceof InternetAddress)
		                                              .map(address -> ((InternetAddress) address).getAddress())
		                                              .filter(address -> address.contains("@"))
		                                              .map(address -> {
		                                                int atIndex = address.indexOf("@");
		                                                return address.substring(0, atIndex + 1) + address.substring(atIndex + 1)
		                                                                                                  .toLowerCase();
		                                              })
		                                              .distinct();
	   return distinctRecipients.map(SMTPMailMessageConverter::uncheckedInternetAddress)
		                                                                     .toArray(InternetAddress[]::new);
	}
	
	private static InternetAddress uncheckedInternetAddress(String address) 
	{
		try 
		{  
			return new InternetAddress(address);
		} 
		catch (AddressException e) 
		{
		    throw new RuntimeException(e);
		}
    }		
	
}