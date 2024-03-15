package com.vmware.tanzu.apps.sso.accelerator.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableConfigurationProperties(OAuth2ClientProperties.class)
public class WebSecurityConfig {

	@Bean(name = "issuerTrust")
	@Order(Ordered.HIGHEST_PRECEDENCE)
	IssuerTrust issuerTrust(OAuth2ClientProperties properties, ApplicationContext context) {
		return new IssuerTrust(properties, context);
	}

	@Bean
	@DependsOn("issuerTrust")
	SecurityFilterChain securityFilterChain(HttpSecurity http, ClientRegistrationRepository clientRegistrationRepository) throws Exception {
		http
				.authorizeHttpRequests(authorizeRequests ->
						authorizeRequests
								// Permit all public paths explicitly.
								.requestMatchers("/", "/home", "/webjars/**", "/styles/**", "/images/**").permitAll()
								// Permit liveness and readiness probes paths
								.requestMatchers("/livez", "/readyz").permitAll()
								// Require authentication for all others paths.
								.anyRequest().authenticated()
				)
				// After a successful logout, redirect to /home.
				.logout(logout -> {
						logout.logoutSuccessUrl("/home");
				})
				.oauth2Login(withDefaults())
				.oauth2Client(withDefaults());
		return http.build();
	}

}
