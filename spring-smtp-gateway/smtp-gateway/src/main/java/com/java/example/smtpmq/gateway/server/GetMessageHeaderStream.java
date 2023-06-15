package com.java.example.smtpmq.gateway.server;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import jakarta.mail.MessagingException;

public class GetMessageHeaderStream
{
	protected int maxHeaderBytes;
	
	public GetMessageHeaderStream(int maxHeaderBytes)
	{
		this.maxHeaderBytes = maxHeaderBytes;
	}
	
	public ByteArrayInputStream getHeaderStream(InputStream stream) throws MessagingException 
	{
		try 
		{
			return getHeaderStreamWithExceptions(stream);
		} 
		catch (IOException e) 
		{
			throw new MessagingException("", e);
		}
	 }

    public ByteArrayInputStream getHeaderStreamWithExceptions(InputStream stream) throws IOException, MessagingException 
    {
		  // Put the headers into a ByteArrayOutputStream
		  ByteArrayOutputStream buffer = new ByteArrayOutputStream(512);
		  int n = stream.read();
		  while (n > -1) 
		  {
		      buffer.write(n);
		      if (buffer.size() > maxHeaderBytes) 
		      {		          
		          throw new MessagingException("Number of header bytes exceeded maximum allowed bytes of " + maxHeaderBytes + ".");
		      }
		        
		      if (n == "\n".getBytes()[0]) 
		      {
		          n = stream.read();
		          if (n == "\r".getBytes()[0]) 
		          {
		            // have read the \r, so putting it in the headerstream
		            buffer.write(n);
		            // this means headers are finished and content is about
		            // to begin so, stop reading the bytes
		            break;
		          }
		      } 
		      else 
		      {
		          n = stream.read();
		      }
		  }

		  return new ByteArrayInputStream(buffer.toByteArray());
    }
}
