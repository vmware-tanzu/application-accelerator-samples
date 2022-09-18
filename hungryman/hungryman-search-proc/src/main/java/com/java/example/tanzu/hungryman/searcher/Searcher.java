package com.java.example.tanzu.hungryman.searcher;

import com.java.example.tanzu.hungryman.model.Availability;
import com.java.example.tanzu.hungryman.model.SearchCriteria;

import reactor.core.publisher.Flux;

public interface Searcher 
{
	public Flux<Availability> search(SearchCriteria crit);
}
