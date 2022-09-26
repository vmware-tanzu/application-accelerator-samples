package com.java.example.tanzu.hungryman.functions;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.function.context.PollableBean;
import org.springframework.context.annotation.Configuration;

import com.java.example.tanzu.hungryman.entity.Search;
import com.java.example.tanzu.hungryman.repository.SearchRepository;

import reactor.core.publisher.Flux;

@Configuration
public class SearchSupplier
{
	@Autowired
	protected SearchRepository searchRepo;
	
	@PollableBean
	public Supplier<Flux<Search>> submittedSearches()
	{
		return () ->
		{
			final var curTime = System.currentTimeMillis();
			
			/*
			 * If the requested end time has passed, then don't search
			 */
			return searchRepo.findAll()
				.filter(search -> search.getEndTime() >= curTime && search.isContinousSearch());
			
		};
	}
	
}
