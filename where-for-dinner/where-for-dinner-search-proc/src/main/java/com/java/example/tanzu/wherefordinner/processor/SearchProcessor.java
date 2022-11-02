package com.java.example.tanzu.wherefordinner.processor;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.java.example.tanzu.wherefordinner.model.Availability;
import com.java.example.tanzu.wherefordinner.model.SearchCriteria;
import com.java.example.tanzu.wherefordinner.processor.cache.HashCache;
import com.java.example.tanzu.wherefordinner.searcher.Searcher;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class SearchProcessor 
{	
    @Autowired
	protected HashCache cache;
	
	@Autowired
	protected Searcher searcher;
	
	public Flux<Availability> search(Flux<SearchCriteria> searches)
	{
		return searches.flatMap(crit ->
			{
				log.info("Processing search for {}", crit.getName());
				
				return searcher.search(crit)
					.flatMap(avail -> 
					{
						/*
						 * Determine if the dining availability has changed or not.  Do
						 * NOT return search results that have not changed.
						 */
						final var key = new StringBuilder("searchResult:")
								.append(avail.getSearchName()).append(":").append(avail.getDiningName()).toString();

						
						return cache.getHashForKey(key)
							.flatMap(value -> 
							{
								final var hash = generateHash(avail);
								
								if (StringUtils.hasText(value))
									if (hash.equals(value))
										return Mono.empty();
	
								/*
								 * Expire the entry after the search window has passed
								 */
								var expiration = Duration.ofMillis(Math.abs(crit.getEndTime() - System.currentTimeMillis()));
								
								return cache.setHashForKey(key, hash, expiration)
										.then(Mono.just(avail));
							});															
				});
			});		
	}
	
	protected String generateHash(Availability avail)
	{
		return Integer.toString(avail.hashCode());
	}
	
}
