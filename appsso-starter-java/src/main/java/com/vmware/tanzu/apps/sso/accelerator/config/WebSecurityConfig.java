package com.vmware.tanzu.apps.sso.accelerator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	private ClientRegistrationRepository clientRegistrationRepository;

	public WebSecurityConfig(ClientRegistrationRepository clientRegistrationRepository) {
		this.clientRegistrationRepository = clientRegistrationRepository;
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				// Add an authentication check filter to enable auto-redirects if the user is already signed in,
				// or if the user is attempting to navigate to protected paths.
				.addFilterAfter(new AuthenticationCheckFilter(), UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests(authorizeRequests ->
						authorizeRequests
								// Permit all public paths explicitly.
								.requestMatchers("/", "/home", "/webjars/**", "/styles/**", "/images/**").permitAll()
								// Require authentication for all others paths.
								.anyRequest().authenticated()
				)
				// After a successful logout, redirect to /home.
				.logout().logoutSuccessHandler(oidcLogoutSuccessHandler()).logoutSuccessUrl("/home")
				.and()
				.oauth2Login(withDefaults())
				.oauth2Client(withDefaults());
		return http.build();
	}

	// see: https://docs.spring.io/spring-security/reference/servlet/oauth2/login/advanced.html#oauth2login-advanced-oidc-logout
	private LogoutSuccessHandler oidcLogoutSuccessHandler() {
		OidcClientInitiatedLogoutSuccessHandler oidcLogoutSuccessHandler =
				new OidcClientInitiatedLogoutSuccessHandler(this.clientRegistrationRepository);

		// Sets the location that the End-User's User Agent will be redirected to
		// after the logout has been performed at the Provider
		oidcLogoutSuccessHandler.setPostLogoutRedirectUri("{baseUrl}");

		return oidcLogoutSuccessHandler;
	}

}
