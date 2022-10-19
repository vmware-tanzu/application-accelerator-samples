package com.vmware.identity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfiguration {

	@Bean
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
		httpSecurity.oauth2ResourceServer()
					.jwt()
				.jwtAuthenticationConverter(new ReactiveJwtAuthenticationConverterAdapter(new UserNameJwtAuthenticationConverter()));

		return httpSecurity.build();
	}
}
