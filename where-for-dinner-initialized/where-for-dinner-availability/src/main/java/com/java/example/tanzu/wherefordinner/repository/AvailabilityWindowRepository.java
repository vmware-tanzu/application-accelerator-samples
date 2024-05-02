package com.java.example.tanzu.wherefordinner.repository;

import java.util.List;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.java.example.tanzu.wherefordinner.entity.AvailabilityWindow;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AvailabilityWindowRepository extends ReactiveCrudRepository<AvailabilityWindow, Long>
{
	public Flux<AvailabilityWindow> findByAvailabilityId(long availabilityId);
	
	public Mono<Void> deleteByAvailabilityId(long availabilityId);
	
	public Mono<Void> deleteByAvailabilityIdIn(List<Long> availabilityIds);
}
