package com.java.example.tanzu.wherefordinner.searcher.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.java.example.tanzu.wherefordinner.exchange.CrawlerClient;
import com.java.example.tanzu.wherefordinner.model.Availability;
import com.java.example.tanzu.wherefordinner.model.SearchCriteria;
import com.java.example.tanzu.wherefordinner.searcher.Searcher;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Component
@Profile("crawler")
@Primary
@Slf4j
public class CrawlerSearcher implements Searcher
{

	@Autowired
	protected CrawlerClient crawlClient;
	
	@Override
	public Flux<Availability> search(SearchCriteria crit) 
	{
		log.info("Making crawler dining search for dining search {}", crit.name());
		
		return crawlClient.search(StringUtils.hasText(crit.diningNames()) ? crit.diningNames() : "", 
				StringUtils.hasText(crit.diningTypes()) ? crit.diningTypes() : "", 
				crit.startTime(), crit.endTime())
			.map(avail -> new Availability(crit.name(), avail.diningName(), avail.address(), avail.locality(), avail.region(), avail.postalCode(), avail.phoneNumber(),
					avail.reservationURL(), crit.requestSubject(), crit.sendResultsTo(), avail.availabilityWindows()));
			
	}

}
