package com.java.example.tanzu.postalsearch.config;

import javax.net.ssl.SSLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.security.oauth2.client.AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.InMemoryReactiveOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizedClientRepository;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.java.example.tanzu.postalsearch.exchange.PostalCodeSearchClient;

import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import reactor.netty.http.client.HttpClient;

@Configuration
public class DeclarativeClientConfig {

	protected final Logger logger = LoggerFactory.getLogger(DeclarativeClientConfig.class);
	
	@Value("${postal-code.search.service.identifier}")
	protected String postCodeSearchServiceIdentifier;
	
	@Value("${postal-code.search.service.ignore-tls-validation:true}")
	protected boolean ignoreTlsValidation;	
	
	@Value("${postal-code.search.service.oauth-registration-id:postal-code-client}")
	protected String oauthRegId;
	
	@Bean
	public ReactiveOAuth2AuthorizedClientManager authorizedClientManager(
			ReactiveClientRegistrationRepository clientRegistrationRepository,
			ServerOAuth2AuthorizedClientRepository authorizedClientRepository) {

		final var authorizedClientProvider = ReactiveOAuth2AuthorizedClientProviderBuilder.builder()
				.clientCredentials()
				.refreshToken()
				.build();

		final var authorizedClientService = new InMemoryReactiveOAuth2AuthorizedClientService(clientRegistrationRepository);
		
		final var authorizedClientManager = new AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager(
						clientRegistrationRepository, authorizedClientService);
		
		authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);

		return authorizedClientManager;
	}
	
	
	@Bean("clientSecurityFilter")
	public ExchangeFilterFunction oauth2ClientAuthFilter(ReactiveOAuth2AuthorizedClientManager authorizedClientManager)
	{
	    final var filter =  new ServerOAuth2AuthorizedClientExchangeFilterFunction(authorizedClientManager);    
	    
	    filter.setDefaultClientRegistrationId(oauthRegId);
	    
	    return filter;
	}
	
	@Bean
	public PostalCodeSearchClient getPostalCodeSearchClient(WebClient.Builder builder, 
			@Qualifier("clientSecurityFilter") ExchangeFilterFunction exchangeFilter)
	{
		builder.baseUrl(postCodeSearchServiceIdentifier).filter(exchangeFilter);
		
		if (ignoreTlsValidation)
		{
			try
			{
				final var sslContext = SslContextBuilder.forClient()
				     .trustManager(InsecureTrustManagerFactory.INSTANCE).build();
				
				final var httpClient = HttpClient.create().secure(t -> t.sslContext(sslContext));
				builder.clientConnector(new ReactorClientHttpConnector(httpClient));
			}
			catch (SSLException e)
			{
				logger.error("Error setting up non certificate validating sslContext", e);
			}
		}
		
		final var client = builder.build();
		final var factory = HttpServiceProxyFactory.builderFor(WebClientAdapter.create(client)).build();

		return factory.createClient(PostalCodeSearchClient.class);
	}
}
