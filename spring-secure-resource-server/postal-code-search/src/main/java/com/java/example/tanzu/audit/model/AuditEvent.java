package com.java.example.tanzu.audit.model;

import java.util.Collection;

public record AuditEvent(String context, String name, String principal, 
		String result, long eventTime, Collection<AuditData> auditData) {

}
