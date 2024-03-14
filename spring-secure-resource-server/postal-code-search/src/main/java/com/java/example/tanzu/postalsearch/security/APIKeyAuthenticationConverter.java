package com.java.example.tanzu.postalsearch.security;


import org.flywaydb.core.internal.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Profile("api-key")
@Component
public class APIKeyAuthenticationConverter implements ServerAuthenticationConverter {

	@Value("${api.key-header:X-API-Key}")
	public String keyHeader;
	
	@Value("${api.key}")
	public String apiKey;
	
	
	@Override
	public Mono<Authentication> convert(ServerWebExchange exchange)
	{
        return Mono.justOrEmpty(exchange)
                .flatMap(serverWebExchange -> Mono.justOrEmpty(extractAPIKey(exchange)))
                .flatMap(apiKey -> validateKey(apiKey));		
	}
	
	private Mono<Authentication> validateKey(String key)
	{
		return Mono.justOrEmpty((key.compareTo(apiKey) == 0) ? new KeyAuthentication(key) : null);
	}
	
	private String extractAPIKey(ServerWebExchange exchange)
	{
		//Check query parameters first
		var extractedKey = exchange.getRequest().getQueryParams().getFirst(keyHeader);
		
		if (!StringUtils.hasText(extractedKey))
			extractedKey = exchange.getRequest().getHeaders().getFirst(keyHeader);
		
		return extractedKey;
	}
	
	static class KeyAuthentication extends AbstractAuthenticationToken
	{
		private static final long serialVersionUID = 5590166344201400192L;

		private final String apiKey;
		
		public KeyAuthentication(String apiKey) {
			super(AuthorityUtils.NO_AUTHORITIES);
			
			this.apiKey = apiKey;
		}

		@Override
		public Object getCredentials() {
			return apiKey;
		}

		@Override
		public Object getPrincipal() {
			return apiKey;
		}
		
	}
}
