package com.java.example.tanzu.audit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverter;
import org.springframework.security.web.server.SecurityWebFilterChain;

import com.java.example.tanzu.audit.security.JwtRolesToAuthoritiesConverter;

@Configuration
public class WebSecurityConfig {

	@Bean
	public ReactiveJwtAuthenticationConverter customJwtAuthenticationConverter() {
		ReactiveJwtAuthenticationConverter converter = new ReactiveJwtAuthenticationConverter();
	    converter.setJwtGrantedAuthoritiesConverter(new JwtRolesToAuthoritiesConverter());
	    return converter;
	}	
	
	@Bean
	public SecurityWebFilterChain oauth2SecurityFilterChain(ServerHttpSecurity http, ReactiveJwtAuthenticationConverter converter)
	{
		http.authorizeExchange(exchanges -> {
			exchanges.pathMatchers("/audit/**").authenticated()
			.anyExchange().permitAll();
			
		})
		.oauth2ResourceServer(server -> server.jwt(jwt -> jwt.jwtAuthenticationConverter(converter)));

		return http.build();
	}
}
