package com.java.example.tanzu.postalsearch.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.java.example.tanzu.postalsearch.entity.GeoLoc;

public interface GeoLocRepository extends ReactiveCrudRepository<GeoLoc, String> {

}
