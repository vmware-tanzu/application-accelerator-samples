package com.java.example.tanzu.wherefordinner.searcher;

import com.java.example.tanzu.wherefordinner.model.Availability;
import com.java.example.tanzu.wherefordinner.model.SearchCriteria;

import reactor.core.publisher.Flux;

public interface Searcher 
{
	public Flux<Availability> search(SearchCriteria crit);
}
