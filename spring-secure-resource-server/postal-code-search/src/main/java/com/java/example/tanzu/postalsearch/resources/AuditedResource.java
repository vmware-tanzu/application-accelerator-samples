package com.java.example.tanzu.postalsearch.resources;

import java.time.Instant;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;

import com.java.example.tanzu.audit.model.AuditData;
import com.java.example.tanzu.audit.model.AuditEvent;

public abstract class AuditedResource {

	protected static final String AUDIT_CONTEXT = "Postal Code Service";	
	
	protected final static String EVENT_SUCCESS = "SUCCESS";

	protected static final String STREAM_BRIDGE_AUDIT_OUTPUT_CHANNLE = "auditEvent-out-0";
	
	@Autowired
	protected StreamBridge streamBridge;
	
    /*
     * Stream to the auditing system
     */
    protected void auditEvent(String eventName, String principal, Collection<AuditData> auditData, String result)
    {    	
    	final var event = new AuditEvent(AUDIT_CONTEXT, eventName, principal, result, Instant.now().toEpochMilli(), auditData);
    	
    	streamBridge.send(STREAM_BRIDGE_AUDIT_OUTPUT_CHANNLE, event);
    }	
		
}
