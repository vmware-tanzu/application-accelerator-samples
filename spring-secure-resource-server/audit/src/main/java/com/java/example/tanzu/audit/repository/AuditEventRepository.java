package com.java.example.tanzu.audit.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;

import com.java.example.tanzu.audit.entity.AuditEvent;

import reactor.core.publisher.Flux;

public interface AuditEventRepository extends ReactiveSortingRepository<AuditEvent, Long>, ReactiveCrudRepository<AuditEvent, Long> {

	public Flux<AuditEvent> findByEventTimeBetween(long startTime, long endTime, Pageable page);
	
	public Flux<AuditEvent> findByEventTimeGreaterThanEqual(long startTime, Pageable page);
}
