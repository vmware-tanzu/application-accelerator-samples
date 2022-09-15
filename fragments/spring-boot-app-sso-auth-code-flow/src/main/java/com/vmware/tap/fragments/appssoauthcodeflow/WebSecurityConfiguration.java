package com.vmware.tap.fragments.appssoauthcodeflow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
public class WebSecurityConfiguration {

    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorize -> authorize
                                // Permit all public paths explicitly
                                .antMatchers("/",
                                        "/livez",
                                        "/readyz",
                                        "/webjars/**",
                                        "/styles/**",
                                        "/images/**",
                                        "/favicon.ico").permitAll()
                                // Secure our Spring MVC Endpoints (basically the rest)
                                .anyRequest().authenticated()
                )
                // After a successful logout, redirect to /.
                .logout(logout ->
                        logout.logoutSuccessHandler(oidcLogoutSuccessHandler())
                              .logoutSuccessUrl("/"))
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
