package com.java.example.smtpmq.gateway.server;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.CountingInputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.StringUtils;
import org.subethamail.smtp.MessageHandler;
import org.subethamail.smtp.RejectException;
import org.subethamail.smtp.TooMuchDataException;

import com.java.example.smtpmq.gateway.streams.SmtpGatewayMessageSource;

public class SMTPMessageHandler implements MessageHandler
{
	private static Logger log = LogManager.getLogger(MessageHandler.class);
	
	protected SmtpGatewayMessageSource messageSource;
	protected GetMessageHeaderStream getMessageHeaderStream;
	protected SizeLimitedStreamCreator sizeLimitedStreamCreator;
	protected List<InternetAddress> recipients;
	protected InternetAddress from;
	
	public SMTPMessageHandler(SmtpGatewayMessageSource messageSource,
			GetMessageHeaderStream getMessageHeaderStream, SizeLimitedStreamCreator sizeLimitedStreamCreator)
	{
	    this.messageSource = messageSource;	    
	    this.recipients = new LinkedList<InternetAddress>();
	    
	    this.getMessageHeaderStream = getMessageHeaderStream;
	    this.sizeLimitedStreamCreator = sizeLimitedStreamCreator;
	}
	
	public void setMessageSource(SmtpGatewayMessageSource source)
	{
		this.messageSource = source;
	}
	
	@Override
	public void from(String from) throws RejectException
	{
	    try 
	    {
	    	if (StringUtils.hasText(from))
	    	{
		        this.from = new InternetAddress(from);
	    		
		    	// handle "<>" scenario
		        if (!StringUtils.hasText(this.from.getAddress())) 
		        {
		        	this.from = null;
		            log.info("blank address... mailFrom will be null");
		        }	
		        
	    	}
	    } 
	    catch (AddressException e)
	    {
	        String errorMessage = "error parsing from address " + from;
	        throw new RejectException(errorMessage);
	    }
		
	}	
	
	@Override
	public void recipient(String recipient) throws RejectException
	{
	    try 
	    {
	        InternetAddress recipientAddress = new InternetAddress(recipient);
	        recipients.add(recipientAddress);
	    } 
	    catch (AddressException e)
	    {
	        String errorMessage = "error parsing recipient address " + recipient;
	        log.error(errorMessage, e);
	        throw new RejectException(errorMessage);
	    }
		
	}

	@Override
	public void data(InputStream data) throws RejectException, TooMuchDataException, IOException
	{
		InputStream msgIn = new PushbackInputStream(data);//new PushbackInputStream(new CharTerminatedInputStream(data, SMTPTerminator));
		
	    MimeMessage mimeMessage;
	    ByteArrayInputStream headerStream = null;
	    try
	    {
	    	CountingInputStream countingInputStream = null;
		    try 
		    {	
		       headerStream = getMessageHeaderStream.getHeaderStream(msgIn);
		       
		       // this will limit the size of the message
		       // and throw and exception if it can't be read
		       // because of size issues
		       msgIn = sizeLimitedStreamCreator.create(headerStream, msgIn);
		       countingInputStream = new CountingInputStream(msgIn);
		      mimeMessage = new MimeMessage(Session.getDefaultInstance(new Properties()), countingInputStream) 
		      {
		        @Override
		        protected void updateMessageID() throws MessagingException 
		        {
		        }
		      };
		    } 
		    catch (MessagingException e) 
		    {
		      String errorMessage = "Error creating MimeMessage from data input stream";
		      
		      if (e.getCause() != null && (e.getCause() instanceof MessageSizeException))
		      {
		    	  /*
		    	   * RFC821:
				   * 552 - Requested mail action aborted: exceeded storage allocation
		    	   */
		    	  errorMessage +=  ": " + e.getCause().getMessage();
		    	  log.error(errorMessage, e);
		    	  throw new RejectException(552, errorMessage);
		      }
		      else
		      {
		    	  /*
		    	   * RFC821:
				   * Service not available, closing transmission channel [This may be a reply to any command if the service knows it must shut down]
		    	   */
		    	  log.error(errorMessage, e);
		    	  throw new RejectException(421, errorMessage);
		      }
		    }		
		    
		    String messageId = "";
		    try 
		    {
		    	final SMTPMailMessage mailMessage = new SMTPMailMessage(mimeMessage, recipients, from);
		    	messageId = mimeMessage.getMessageID();
		    	messageSource.forwardSMTPMessage(mailMessage);

		        log.info("successfully sent message with message id {} ({} bytes)", messageId, countingInputStream.getByteCount());
		    } 
		    catch (Throwable e) 
		    {
		    	/*
		    	 * RFC821:
		         * Transaction failed
		         */
		        log.error("error sending message with message id " + messageId, e);
		        throw new RejectException(554, "Error sending message: " + e.getMessage());
		    }
	    }
	    finally
	    {
	    	IOUtils.closeQuietly(headerStream);
	    }
	}
	
	@Override
	public void done()
	{

	}	
}
