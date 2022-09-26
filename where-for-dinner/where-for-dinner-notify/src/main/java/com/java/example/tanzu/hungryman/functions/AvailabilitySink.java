package com.java.example.tanzu.hungryman.functions;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.java.example.tanzu.hungryman.model.Availability;
import com.java.example.tanzu.hungryman.publisher.Publisher;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Configuration
@Slf4j
public class AvailabilitySink 
{
	@Autowired
	protected List<Publisher> publishers;
	
	@Bean
	public Function<Flux<Availability>, Mono<Void>> notifyAvailability()
	{
		return avails -> avails.flatMap(avail -> 
		{
			return Flux.fromIterable(publishers)
				.flatMap(pub ->
				{
					return pub.publishAvailability(avail);
				})
				.onErrorResume(e -> {
					log.error("Error publishing to publisher", e);
					return Mono.empty();
				});
		})
		.then();
	}
}
