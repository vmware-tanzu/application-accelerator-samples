package com.java.example.smtpmq.gateway.springconfig;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.subethamail.smtp.MessageContext;
import org.subethamail.smtp.MessageHandler;
import org.subethamail.smtp.MessageHandlerFactory;
import org.subethamail.smtp.server.SMTPServer;

import com.java.example.smtpmq.gateway.server.GetMessageHeaderStream;
import com.java.example.smtpmq.gateway.server.SMTPMessageHandler;
import com.java.example.smtpmq.gateway.server.SizeLimitedInputStreamFactory;
import com.java.example.smtpmq.gateway.server.SizeLimitedStreamCreator;
import com.java.example.smtpmq.gateway.server.SafelistedServerSocket;
import com.java.example.smtpmq.gateway.streams.SmtpGatewayMessageSource;

@Configuration
public class SMTPServerBeanConfig
{	
	
	@Value("${smtpmqgateway.binding.port:1026}")
	public int port;
	
	@Value("${smtpmqgateway.binding.host:0.0.0.0}")
	public String host;	
	
	@Value("${smtpmqgateway.message.maxHeaderSize:262144}")
	private int maxHeaderSize;			
	 
	@Value("${smtpmqgateway.message.maxMessageSize:39845888}")
	private int maxMessageSize;			
	
	
	@Value("${smtpmqgateway.clientsafelist.cidr:}")
	private List<String> clientSafelistCidrs;	
	
	@Autowired
	protected SmtpGatewayMessageSource messageSourceQueue;
	
	@Bean(destroyMethod = "stop")
    public SMTPServer smtpServer() throws Exception
    {
		final SMTPServer smtpServer = new SMTPServer(new MessageHandlerFactory() 
	    {
			@Override
			public MessageHandler create(MessageContext ctx) 
			{
				return smtpMessageHandler();		
			}
	        
        })
		{
			@Override
			protected ServerSocket createServerSocket() throws IOException
			{
				if (clientSafelistCidrs.isEmpty() || 
						(clientSafelistCidrs.size() == 1 && !StringUtils.hasText(clientSafelistCidrs.get(0))))
					return super.createServerSocket();
				
				InetSocketAddress isa;
	
				if (this.getBindAddress() == null)
				{
					isa = new InetSocketAddress(this.getPort());
				}
				else
				{
					isa = new InetSocketAddress(this.getBindAddress(), this.getPort());
				}
	
				final ServerSocket serverSocket = new SafelistedServerSocket(clientSafelistCidrs);
				serverSocket.bind(isa, this.getBacklog());
	
				if (this.getPort() == 0)
				{
					this.setPort(serverSocket.getLocalPort());
				}
	
				return serverSocket;
			}				
		};
		
		smtpServer.setPort(port);
		smtpServer.setBindAddress(InetAddress.getByName(host));
		smtpServer.setSoftwareName("Spring SMTP Gateway");
		smtpServer.setMaxMessageSize(maxMessageSize);
		return smtpServer;
    }
	
	@Bean
	public SMTPMessageHandler smtpMessageHandler()
	{	
		final SizeLimitedStreamCreator sizeCreator = new SizeLimitedStreamCreator(maxMessageSize,
				SizeLimitedInputStreamFactory.getInstance());	
		
		return new SMTPMessageHandler(messageSourceQueue, new GetMessageHeaderStream(maxHeaderSize), sizeCreator);
	}		
}
