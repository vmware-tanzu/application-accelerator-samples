package com.java.example.tanzu.hungryman.functions;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.java.example.tanzu.hungryman.model.Availability;
import com.java.example.tanzu.hungryman.model.SearchCriteria;
import com.java.example.tanzu.hungryman.processor.SearchProcessor;

import reactor.core.publisher.Flux;

@Configuration
public class Search
{
	@Autowired
	private SearchProcessor proc;
	
	@Bean
	public Function<Flux<SearchCriteria>, Flux<Availability> > processSearch()
	{
		return search ->
		{
			return proc.search(search);
		};
	}
}
