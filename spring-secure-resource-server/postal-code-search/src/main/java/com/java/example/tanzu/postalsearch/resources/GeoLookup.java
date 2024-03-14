package com.java.example.tanzu.postalsearch.resources;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.example.tanzu.postalsearch.entity.GeoLoc;
import com.java.example.tanzu.postalsearch.repository.GeoLocRepository;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("geolookup")
public class GeoLookup {

	@Value("${postal-codes.roles.premium:ROLE_PREMIUM_USER}")
	protected String premiumRole;
	
	@Autowired
	protected GeoLocRepository repo;
	
	@GetMapping
	public Mono<GeoLoc> lookup(@AuthenticationPrincipal Principal principal, 
			@RequestParam(name="postalCode") String postalCode)
	{
		
		return repo.findById(postalCode)
			.filter(geoloc ->  (!geoloc.premium() || (geoloc.premium() && isPremiumUser(principal))));
	}
	
	
	private boolean isPremiumUser(Principal prin)
	{
		if (prin instanceof JwtAuthenticationToken)
		{
			final JwtAuthenticationToken token = JwtAuthenticationToken.class.cast(prin);
			
			for (GrantedAuthority auth : token.getAuthorities())
				if (premiumRole.compareTo(auth.getAuthority()) == 0)
					return true;
		}
		/*
		 * TODO: Handle API Key use case
		 */
		
		return false;
	}
}
