package com.java.example.tanzu.hungryman.feign;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.example.tanzu.hungryman.model.Availability;

import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Flux;

@Profile("crawler")
@ReactiveFeignClient(name = "${hungryman.crawler.service.name:hungryman-crawler}", url = "${hungryman.crawler.service.url:}")
public interface CrawlerClient 
{
	@GetMapping("/search")
	public Flux<Availability> search(@RequestParam("diningNames") String diningNames, @RequestParam("diningTypes") String diningTypes);
}
