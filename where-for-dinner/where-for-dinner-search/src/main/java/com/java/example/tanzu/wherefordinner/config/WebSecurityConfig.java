package com.java.example.tanzu.wherefordinner.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class WebSecurityConfig 
{
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
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) 
    {
	   http.authorizeExchange()
	   .pathMatchers("/search/**").authenticated()
	   .anyExchange().permitAll()
	   .and()
	   .oauth2ResourceServer().jwt();
       http.csrf().disable();
       return http.build();
    }
}
