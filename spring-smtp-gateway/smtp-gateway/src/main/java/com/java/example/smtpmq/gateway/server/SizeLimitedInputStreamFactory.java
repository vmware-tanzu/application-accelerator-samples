package com.java.example.smtpmq.gateway.server;

import java.io.InputStream;

public class SizeLimitedInputStreamFactory
{
    private static SizeLimitedInputStreamFactory instance = new SizeLimitedInputStreamFactory();

    /**
     * Return instance of the object
     * 
     * @return SizeLimitedInputStreamFactory
     */
    public static SizeLimitedInputStreamFactory getInstance() 
    {
    	return instance;
    }

    /**
     * create SizeLimitedInputStream
     * 
     * @param in
     * @param maxmessagesize
     * @return {@link SizeLimitedInputStream}
     */
    public SizeLimitedInputStream create(InputStream in, long maxmessagesize) 
    {
    	return new SizeLimitedInputStream(in, maxmessagesize);
    }
}
