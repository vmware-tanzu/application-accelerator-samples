package com.java.example.tanzu.wherefordinner.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.java.example.tanzu.wherefordinner.entity.Availability;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AvailabilityRepository extends ReactiveCrudRepository<Availability, Long>
{
	public Mono<Availability> findBySearchNameAndDiningNameAndRequestSubject(String searchName, String diningName, String subject);
	
	public Flux<Availability> findBySearchNameAndRequestSubject(String searchName, String subject);
	
	public Flux<Availability> findByRequestSubject(String subject);
}
