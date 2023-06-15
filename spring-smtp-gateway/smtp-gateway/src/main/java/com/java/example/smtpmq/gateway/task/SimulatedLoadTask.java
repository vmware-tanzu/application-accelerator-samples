package com.java.example.smtpmq.gateway.task;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Properties;

import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.java.example.smtpmq.gateway.server.SMTPMailMessage;
import com.java.example.smtpmq.gateway.streams.SmtpGatewayMessageSource;

@ConditionalOnProperty(name="smtpmqgateway.loadgen.rate", matchIfMissing=false)
@Component
public class SimulatedLoadTask
{
	private static Logger log = LogManager.getLogger(SimulatedLoadTask.class);
	
	@Value("${smtpmqgateway.loadgen.sender}")
	protected String sender;
	
	@Value("${smtpmqgateway.loadgen.recipient}")
	protected String recipient;	
	
	@Autowired
	protected SmtpGatewayMessageSource msgSource;
	
	@Scheduled(fixedRateString = "${smtpmqgateway.loadgen.rate}", initialDelayString = "${smtpmqgateway.loadgen.initialdelay:5000}")
	public void sendMessages() throws Exception 
	{
		final MimeMessage msg = new MimeMessage(Session.getDefaultInstance(new Properties()));
		msg.setFrom(new InternetAddress(sender));
		msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
		msg.setSentDate(Calendar.getInstance().getTime());
		msg.setText("Load Test");
		
		SMTPMailMessage smptMsg = new SMTPMailMessage(msg, Arrays.asList(new InternetAddress(recipient)), new InternetAddress(sender));
		
		log.info("Sending generated load from " + sender + " to " + recipient);
		
		msgSource.forwardSMTPMessage(smptMsg);
	}
}
