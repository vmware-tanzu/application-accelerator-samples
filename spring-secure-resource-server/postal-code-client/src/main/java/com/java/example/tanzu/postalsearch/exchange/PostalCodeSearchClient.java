package com.java.example.tanzu.postalsearch.exchange;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

import reactor.core.publisher.Mono;

public interface PostalCodeSearchClient {

	@GetExchange("geolookup")
	public Mono<PostalCodeLoc> search(@RequestParam(name="postalCode") String postalCode);
	
}
