package com.vmware.tap.fragments.appssoauthcodeflow;

import static org.mockito.Mockito.mock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class TestWebSecurityConfiguration {

    @Bean
    ClientRegistrationRepository mockClientRestrationRepository() {
        return mock(ClientRegistrationRepository.class);
    }
}
