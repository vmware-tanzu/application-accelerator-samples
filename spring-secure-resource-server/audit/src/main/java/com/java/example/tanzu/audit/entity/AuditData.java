package com.java.example.tanzu.audit.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("auditData")
public record AuditData(@Id Long id, String name, String val, @Column("auditId") Long auditId) 
{
}