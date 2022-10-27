package com.java.example.tanzu.wherefordinner.functions;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.function.context.PollableBean;
import org.springframework.context.annotation.Configuration;

import com.java.example.tanzu.wherefordinner.entity.Search;
import com.java.example.tanzu.wherefordinner.repository.SearchRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Configuration
@Slf4j
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
			
			log.info("Gathering and sending all active searches to downstream processing");
			
			/*
			 * If the requested end time has passed, then don't search
			 */
			return searchRepo.findAll()
				.filter(search -> search.getEndTime() >= curTime && search.isContinousSearch());
			
		};
	}
	
}
