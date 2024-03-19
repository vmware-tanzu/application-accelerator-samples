package com.java.example.tanzu.audit.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.java.example.tanzu.audit.entity.AuditData;

import reactor.core.publisher.Flux;

public interface AuditDataRepository extends ReactiveCrudRepository<AuditData, Long> {
	
	public Flux<AuditData> findByAuditId(long auditId);
}