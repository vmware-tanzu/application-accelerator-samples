package com.vmware.tap.fragments.appssoauthcodeflow;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
@Configuration
public class WebSecurityConfiguration {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                                // Permit all public paths explicitly
                                .requestMatchers("/",
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
                .logout(logout -> logout.logoutSuccessUrl("/"))
                .oauth2Login(withDefaults());
        return http.build();
    }

}
