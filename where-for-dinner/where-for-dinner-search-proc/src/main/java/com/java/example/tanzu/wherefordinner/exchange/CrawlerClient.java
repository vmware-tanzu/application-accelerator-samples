package com.java.example.tanzu.wherefordinner.exchange;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

import com.java.example.tanzu.wherefordinner.model.Availability;

import reactor.core.publisher.Flux;

public interface CrawlerClient 
{
	@GetExchange("/search")
	public Flux<Availability> search(@RequestParam("diningNames") String diningNames, @RequestParam("diningTypes") String diningTypes,
			@RequestParam("startTime") Long startTime, @RequestParam("endTime") Long endTime);
}
