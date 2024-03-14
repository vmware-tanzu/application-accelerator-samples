package com.java.example.tanzu.postalsearch.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.util.StringUtils;

import reactor.core.publisher.Flux;


public class JwtRolesToAuthoritiesConverter implements Converter<Jwt, Flux<GrantedAuthority>>
{

	private static final Collection<String> ROLE_CLAIM_NAMES = Arrays.asList("roles");
	
	private static final String AUTH_PREFIX = "ROLE_%s";
	
	
	@Override
	public Flux<GrantedAuthority> convert(Jwt source)
	{		
		return Flux.fromStream(getAuthorities(source).stream())
                .map(role -> AUTH_PREFIX.formatted(role))
                .map(SimpleGrantedAuthority::new);			
		
	}

	private Collection<String> getAuthorities(Jwt source)
	{
		final var claimName = getClaimName(source);
		
		if (!StringUtils.hasText(claimName))
			return Collections.emptyList();
		
		final var claim = source.getClaim(claimName);
		
		if (claim instanceof String) 
		{
			final String stringClaim = String.class.cast(claim);
			if (StringUtils.hasText((String) claim)) 
				return Arrays.asList((stringClaim).split(" "));

			return Collections.emptyList();
		}
		else if (claim instanceof Collection)
			return castAuthoritiesToCollection(claim);

		
		return Collections.emptyList();
	}
	
	@SuppressWarnings("unchecked")
	private Collection<String> castAuthoritiesToCollection(Object authorities) {
		return (Collection<String>) authorities;
	}	
	
	private String getClaimName(Jwt jwt)
	{
		for (String claimName : ROLE_CLAIM_NAMES)
			if (jwt.hasClaim(claimName))
				return claimName; 
			
		return null;
	}
	
}
