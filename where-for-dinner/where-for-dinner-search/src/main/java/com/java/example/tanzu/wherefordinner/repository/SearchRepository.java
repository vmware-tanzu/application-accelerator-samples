package com.java.example.tanzu.wherefordinner.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.java.example.tanzu.wherefordinner.entity.Search;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SearchRepository extends ReactiveCrudRepository<Search, Long>
{
	public Mono<Search> findByNameIgnoreCaseAndRequestSubject(String name, String subject);
	
	public Flux<Search> findByRequestSubject(String subject);
} 
