package com.java.example.smtpsink.functions;

import java.util.List;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

/**
 * Wraps an SMTP mail message which includes the mime message and the SMTP
 * rcpt_to and mail_from headers.
 * @author Greg Meyer
 */
public class SMTPMailMessage
{
	  private final MimeMessage mimeMessage;
	  private final List<InternetAddress> recipientAddresses;
	  private final InternetAddress mailFrom;

      public SMTPMailMessage(MimeMessage mimeMessage, List<InternetAddress> recipientAddresses, InternetAddress mailFrom) 
      {
    	  this.mimeMessage = mimeMessage;
    	  this.recipientAddresses = recipientAddresses;
    	  this.mailFrom = mailFrom;
	  }

      public MimeMessage getMimeMessage()
      {
    	  return mimeMessage;
      }
      
      public List<InternetAddress> getRecipientAddresses()
      {
    	  return recipientAddresses;
      }
      
	  public InternetAddress getMailFrom() 
	  {
		  return mailFrom;
	  }
}
