package com.java.example.tanzu.wherefordinner.feign;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.example.tanzu.wherefordinner.model.Availability;

import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Flux;

@Profile("crawler")
@ReactiveFeignClient(name = "${where-for-dinner.crawler.service.name:where-for-dinner-crawler}", url = "${where-for-dinner.crawler.service.url:}")
public interface CrawlerClient 
{
	@GetMapping("/search")
	public Flux<Availability> search(@RequestParam("diningNames") String diningNames, @RequestParam("diningTypes") String diningTypes,
			@RequestParam("startTime") Long startTime, @RequestParam("endTime") Long endTime);
}
