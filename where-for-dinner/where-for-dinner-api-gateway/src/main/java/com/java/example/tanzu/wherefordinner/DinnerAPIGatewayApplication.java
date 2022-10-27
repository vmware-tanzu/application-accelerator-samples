package com.java.example.tanzu.wherefordinner;

import java.net.URI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.oidc.web.server.logout.OidcClientInitiatedServerLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.logout.DelegatingServerLogoutHandler;
import org.springframework.security.web.server.authentication.logout.SecurityContextServerLogoutHandler;
import org.springframework.security.web.server.authentication.logout.ServerLogoutHandler;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;
import org.springframework.security.web.server.authentication.logout.WebSessionServerLogoutHandler;

@SpringBootApplication
public class DinnerAPIGatewayApplication 
{		
	public static void main(String[] args) 
	{
		SpringApplication.run(DinnerAPIGatewayApplication.class, args);
	}
	
	@Profile("!secure")
	@Bean
	public SecurityWebFilterChain springUnsecuritySecurityFilterChain(ServerHttpSecurity http) {
	    http.httpBasic().disable();
        http.authorizeExchange().anyExchange().permitAll();
        http.csrf().disable();
        return http.build();
	}	
	
	@Profile("secure")
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http, ReactiveClientRegistrationRepository clientRegistrationRepository) 
    {
	   http.authorizeExchange().pathMatchers("/diningsearch", "/signin", "/api/search/search/**", "/api/availability/availability/**")
	   .authenticated()
	   .pathMatchers("/**").permitAll()
	   .and()
	   .oauth2Login(Customizer.withDefaults())
       .logout()
       .logoutUrl("/scg-logout")
       .logoutHandler(logoutHandler())
       .logoutHandler(new WebSessionServerLogoutHandler())
       .logoutSuccessHandler(oidcLogoutSuccessHandler(clientRegistrationRepository));
       http.csrf().disable();
       return http.build();
    }
    
    private ServerLogoutHandler logoutHandler() {
        return new DelegatingServerLogoutHandler(new WebSessionServerLogoutHandler(), new SecurityContextServerLogoutHandler());
    }

    private ServerLogoutSuccessHandler oidcLogoutSuccessHandler(ReactiveClientRegistrationRepository clientRegistrationRepository) {
        OidcClientInitiatedServerLogoutSuccessHandler logoutSuccessHandler = new OidcClientInitiatedServerLogoutSuccessHandler(clientRegistrationRepository);
        logoutSuccessHandler.setPostLogoutRedirectUri("{baseUrl}");
        logoutSuccessHandler.setLogoutSuccessUrl(URI.create("/"));
        return logoutSuccessHandler;
    }
}
