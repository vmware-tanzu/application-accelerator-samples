package com.java.example.tanzu.wherefordinner.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.java.example.tanzu.wherefordinner.exchange.CrawlerClient;

@Profile("crawler")
@Configuration
public class DeclarativeClientConfig 
{
	@Value("${where-for-dinner.crawler.service.identifier:http://where-for-dinner-crawler}")
	protected String crawlerServiceIdentifier;
	
	@LoadBalanced
	@Bean
	public WebClient.Builder webClientBuilder() 
	{
	    return WebClient.builder();
	 }
	
	@Bean
	public CrawlerClient getCralwerClient(WebClient.Builder builder)
	{
		final var client = builder.baseUrl(crawlerServiceIdentifier).build();
		final var factory = HttpServiceProxyFactory.builderFor(WebClientAdapter.create(client)).build();

		return factory.createClient(CrawlerClient.class);
	}
}
