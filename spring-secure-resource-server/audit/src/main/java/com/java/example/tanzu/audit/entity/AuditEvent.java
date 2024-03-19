package com.java.example.tanzu.audit.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("auditEvent")
public record AuditEvent(@Id Long id, String context, String name, String principal, String result, @Column("eventTime") long eventTime) 
{
}