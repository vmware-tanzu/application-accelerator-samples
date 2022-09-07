package com.java.example.smtpmq.gateway.streams;

import java.util.List;

import javax.mail.internet.InternetAddress;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nhindirect.common.mail.SMTPMailMessage;
import org.nhindirect.common.mail.streams.SMTPMailMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;


@Component
public class SmtpGatewayMessageSource
{	
	private static Logger log = LogManager.getLogger(SmtpGatewayMessageSource.class);
	
	// Maps to the Spring Cloud Stream functional output binding name.
	protected static final String OUT_BINDING_NAME = "smtp-gateway-message-out-0";
	
	@Autowired
	private StreamBridge streamBridge;
	
	public <T> void forwardSMTPMessage(SMTPMailMessage msg) throws Exception
	{
		final String from = (msg.getMailFrom() == null) ? null : msg.getMailFrom().toString();
		
		log.info("Handing off incoming message to smtp gateway for from {} to {} with message id {}", from, 
				toRecipsPrettingString(msg.getRecipientAddresses()), msg.getMimeMessage().getMessageID());
		
		streamBridge.send(OUT_BINDING_NAME, SMTPMailMessageConverter.toStreamMessage(msg));
	}

	protected String toRecipsPrettingString(List<InternetAddress> recips)
	{
		final String[] addrs = new String[recips.size()];
		
		int idx = 0;
		for (InternetAddress addr : recips)
			addrs[idx++] = addr.toString();
		
		return String.join(",", addrs);
	}
	
}
