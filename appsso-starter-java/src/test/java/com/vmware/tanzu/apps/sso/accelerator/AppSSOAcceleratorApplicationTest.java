package com.vmware.tanzu.apps.sso.accelerator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestAcceleratorConfiguration.class)
@ExtendWith(MockitoExtension.class)
public class AppSSOAcceleratorApplicationTest {
	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("Accelerator loads home page")
	void homePageLoads() throws Exception {
		mockMvc.perform(get("/home")).andExpect(status().isOk());
	}
}

@TestConfiguration
class TestAcceleratorConfiguration {
	@Bean
	ClientRegistrationRepository clientRegistrationRepository() {
		var clientRegistration = ClientRegistration
				.withRegistrationId("test-client")
				.clientId("test-client-id")
				.clientSecret("secret")
				.redirectUri("https://myapp.example.com")
				.issuerUri("https://test.example.com")
				.userNameAttributeName(IdTokenClaimNames.SUB)
				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
				.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
				.authorizationUri("https://test.example.com/oauth/authorize")
				.tokenUri("https://test.example.com/oauth/token")
				.jwkSetUri("https://test.example.com/oauth/jwks")
				.build();

		return new InMemoryClientRegistrationRepository(clientRegistration);
	}
}
