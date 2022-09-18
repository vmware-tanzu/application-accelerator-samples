package com.java.example.smtpmq.gateway.server;

import java.io.IOException;
import java.io.InputStream;


/**
 * Taken from the apache james 2.3.2 server which is no longer in the main maven repository.
 *
 */
public class SizeLimitedInputStream extends InputStream
{
	   /**
     * Maximum number of bytes to read.
     */
    private long maxmessagesize = 0;
    /**
     * Running total of bytes read from wrapped stream.
     */
    private long bytesread = 0;

    /**
     * InputStream that will be wrapped.
     */
    private InputStream in = null;

    /**
     * Constructor for the stream. Wraps an underlying stream.
     * @param in InputStream to use as basis for new Stream.
     * @param maxmessagesize Message size limit, in Kilobytes
     */
    public SizeLimitedInputStream(InputStream in, long maxmessagesize) {
        this.in = in;
        this.maxmessagesize = maxmessagesize;
    }

    /**
     * Overrides the read method of InputStream to call the read() method of the
     * wrapped input stream.
     * @throws IOException Throws a MessageSizeException, which is a sub-type of IOException
     * @return Returns the number of bytes read.
     */
    public int read(byte[] b, int off, int len) throws IOException {
        int l = in.read(b, off, len);

        bytesread += l;

        if (maxmessagesize > 0 && bytesread > maxmessagesize) {
            throw new MessageSizeException();
        }

        return l;
    }

    /**
     * Overrides the read method of InputStream to call the read() method of the
     * wrapped input stream.
     * @throws IOException Throws a MessageSizeException, which is a sub-type of IOException.
     * @return Returns the int character value of the byte read.
     */
    public int read() throws IOException {
        if (maxmessagesize > 0 && bytesread <= maxmessagesize) {
            bytesread++;
            return in.read();
        } else {
            throw new MessageSizeException();
        }
    }
}
