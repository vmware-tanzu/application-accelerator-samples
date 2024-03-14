package com.java.example.tanzu.postalsearch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;

import com.java.example.tanzu.postalsearch.security.APIKeyAuthenticationConverter;
import com.java.example.tanzu.postalsearch.security.APIKeyAuthenticationManager;
import com.java.example.tanzu.postalsearch.security.JwtRolesToAuthoritiesConverter;


@Configuration
public class WebSecurityConfig {

	@Bean
	@Profile("oauth2")
	public ReactiveJwtAuthenticationConverter customJwtAuthenticationConverter() {
		ReactiveJwtAuthenticationConverter converter = new ReactiveJwtAuthenticationConverter();
	    converter.setJwtGrantedAuthoritiesConverter(new JwtRolesToAuthoritiesConverter());
	    return converter;
	}	
	
	@Profile("oauth2")
	@Bean
	public SecurityWebFilterChain oauth2SecurityFilterChain(ServerHttpSecurity http, ReactiveJwtAuthenticationConverter converter)
	{
		http.authorizeExchange(exchanges -> {
			exchanges.pathMatchers("/geolookup/**").authenticated()
			.anyExchange().permitAll();
			
		})
		.oauth2ResourceServer(server -> server.jwt(jwt -> jwt.jwtAuthenticationConverter(converter)));

		return http.build();
	}
	
	@Profile("api-key")
	@Bean
	public SecurityWebFilterChain apiKeySecurityFilterChain(ServerHttpSecurity http, APIKeyAuthenticationManager apiKeyAuthManger, APIKeyAuthenticationConverter converter)
	{
		final var authFilter = new AuthenticationWebFilter(apiKeyAuthManger);
		authFilter.setServerAuthenticationConverter(converter);
		
		http.authorizeExchange(exchanges -> {
			exchanges.pathMatchers("/geolookup/**").authenticated()
			.anyExchange().permitAll();
			
		})
		.addFilterAfter(authFilter, SecurityWebFiltersOrder.AUTHENTICATION);
		
		return http.build();
	}

}
