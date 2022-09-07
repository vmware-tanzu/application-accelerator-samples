package com.java.example.smtpmq.gateway.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.util.SubnetUtils;

public class WhitelistedServerSocket extends ServerSocket
{
	protected List<SubnetUtils> cidrChecks;
	
	public WhitelistedServerSocket(List<String> cidrs) throws IOException
	{
		super();
		
		cidrChecks = new ArrayList<>(cidrs.size());
		cidrs.forEach(cidr -> cidrChecks.add(new SubnetUtils(cidr)));
	}
	
	@Override
	public Socket accept() throws IOException
	{
		final Socket retVal = super.accept();
		
		final SocketAddress addr = retVal.getRemoteSocketAddress();
		if (addr == null)
			throw new IOException("Null remote socket address");
		
		final InetSocketAddress inetAddr = InetSocketAddress.class.cast(addr);
		
		boolean isAuthorizedIp = false;
		for (SubnetUtils checker : cidrChecks)
		{
			if (checker.getInfo().isInRange(inetAddr.getAddress().getHostAddress()))
			{
				isAuthorizedIp = true;
				break;
			}
		}
			
		if (!isAuthorizedIp)
		{
			retVal.close();
			throw new IOException("Connection attempted from " + addr.toString() + ".  This address is not authorized");
		}
		
		return retVal;
	}
}
