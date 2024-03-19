package com.java.example.tanzu.postalsearch.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.java.example.tanzu.postalsearch.entity.PotalCodeLoc;

public interface PostalCodeLocRepository extends ReactiveCrudRepository<PotalCodeLoc, String> {
	
}
