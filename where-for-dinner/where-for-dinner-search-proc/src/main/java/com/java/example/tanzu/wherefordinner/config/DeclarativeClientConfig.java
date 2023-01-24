package com.java.example.tanzu.wherefordinner.config;

import org.springframework.beans.factory.annotation.Value;
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
	@Value("${where-for-dinner.crawler.service.identifier:hungryman-crawler}")
	protected String crawlerServiceIdentifier;
	
	@Bean
	public CrawlerClient getCralwerClient()
	{
		final var client = WebClient.builder().baseUrl(crawlerServiceIdentifier).build();
		final var factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(client)).build();

		return factory.createClient(CrawlerClient.class);
	}
}
