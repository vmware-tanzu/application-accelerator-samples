package com.java.example.smtpmq.gateway.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;

import javax.mail.MessagingException;

public class SizeLimitedStreamCreator
{
	private int maxMessageSize;
	private SizeLimitedInputStreamFactory sizeLimitedInputStreamFactory;
	
	public SizeLimitedStreamCreator(int maxMessageSize, SizeLimitedInputStreamFactory sizeLimitedInputStreamFactory) 
	{
	    this.maxMessageSize = maxMessageSize;
	    this.sizeLimitedInputStreamFactory = sizeLimitedInputStreamFactory;
    }

	public InputStream create(InputStream headerStream, InputStream contentStream) throws MessagingException 
	{
		try 
		{
			return createWithExceptions(headerStream, contentStream);
		} 
		catch (IOException e) 
		{
			throw new MessagingException("", e);
		}
    }

    protected InputStream createWithExceptions(InputStream headerStream, InputStream contentStream) throws IOException 
    {
		// if the message size limit has been set, we'll
		// wrap msgIn with a SizeLimitedInputStream
		if (maxMessageSize > 0) 
		{
			// reset headers stream as we need to read it again
			headerStream.reset();

			// merge headers and contents input streams into one stream
			SequenceInputStream mergedStream = new SequenceInputStream(headerStream, contentStream);
			return sizeLimitedInputStreamFactory.create(mergedStream, maxMessageSize);
		} 
		else 
		{

			// reset headers stream as we need to read it again
			headerStream.reset();
			return new SequenceInputStream(headerStream, contentStream);
		} 
	}

	public int getMaxMessageSize() 
	{
		return maxMessageSize;
    }
}
