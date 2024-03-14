package com.java.example.tanzu.postalsearch.security;

import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Profile("api-key")
@Component
public class APIKeyAuthenticationManager implements ReactiveAuthenticationManager {

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
    	
        return Mono.fromSupplier(() -> {
            if (authentication != null && authentication instanceof APIKeyAuthenticationConverter.KeyAuthentication) {
                authentication.setAuthenticated(true);
            }

            return authentication;
        });
    }

}
