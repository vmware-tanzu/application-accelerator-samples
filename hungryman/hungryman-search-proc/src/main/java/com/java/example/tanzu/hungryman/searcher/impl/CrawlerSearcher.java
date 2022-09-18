package com.java.example.tanzu.hungryman.searcher.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.java.example.tanzu.hungryman.feign.CrawlerClient;
import com.java.example.tanzu.hungryman.model.Availability;
import com.java.example.tanzu.hungryman.model.SearchCriteria;
import com.java.example.tanzu.hungryman.searcher.Searcher;

import reactor.core.publisher.Flux;

@Component
@Profile("crawler")
@Primary
public class CrawlerSearcher implements Searcher
{

	@Autowired
	protected CrawlerClient crawlClient;
	
	@Override
	public Flux<Availability> search(SearchCriteria crit) 
	{
		return crawlClient.search(crit.getDiningNames(), crit.getDiningTypes())
			.map(avail -> 
			{
				avail.setSearchName(crit.getName());
				
				return avail;
			});
			
	}

}
