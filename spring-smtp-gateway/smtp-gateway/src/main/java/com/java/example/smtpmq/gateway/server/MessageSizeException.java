package com.java.example.smtpmq.gateway.server;

import java.io.IOException;

/**
 * Taken from the apache james 2.3.2 server which is no longer in the main maven repository.
 *
 */
public class MessageSizeException extends IOException
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 420903468326823983L;

	/**
     * Sole contructor for this class.  This constructor sets
     * the exception message to a fixed error message.
     */
    public MessageSizeException() {
        super("Message size exceeds fixed maximum message size.");
    }
}
