package com.java.example.tanzu.postalsearch.resources;

import java.security.Principal;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.java.example.tanzu.audit.model.AuditData;
import com.java.example.tanzu.postalsearch.entity.PotalCodeLoc;
import com.java.example.tanzu.postalsearch.repository.PostalCodeLocRepository;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("geolookup")
public class PostalCodeLookup extends AuditedResource {

	private final static String LOOKUP_SINGLE_POSTAL_CODE = "Lookup Single Postal Code";

	protected final Logger log = LoggerFactory.getLogger(PostalCodeLookup.class);
	
	@Value("${postal-codes.api.roles.premium:ROLE_PREMIUM_USER}")
	protected String premiumRole;
	
	@Autowired
	protected PostalCodeLocRepository repo;
	
	@GetMapping
	public Mono<PotalCodeLoc> lookup(@AuthenticationPrincipal Principal principal, 
			@RequestParam(name="postalCode") String postalCode)
	{
		
		return repo.findById(postalCode)
			.filter(geoloc ->  (!geoloc.premium() || (geoloc.premium() && isPremiumUser(principal))))
			.doOnSuccess(s -> auditEvent(LOOKUP_SINGLE_POSTAL_CODE, principal.getName(), 
					Collections.singletonList(new AuditData("Postal Code", postalCode)), 
					(s != null) ? EVENT_SUCCESS : EVENT_CODE_NOT_FOUND))
	 	    .onErrorResume(e -> { 
	 	    	
	 	    	auditEvent(LOOKUP_SINGLE_POSTAL_CODE, principal.getName(), 
						Collections.singletonList(new AuditData("Postal Code", postalCode)), EVENT_ERROR); 	    	
	 	    	
		    	log.error("Error performing postal code search.", e);
		    	return Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));
		    });
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
