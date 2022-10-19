package com.vmware.identity;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockOidcLogin;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class WhoAmIControllerTests {

	@Autowired
	WebTestClient webTestClient;

	@Test
	void shouldIncludeUserAttributesForToken() {

		webTestClient
				.mutateWith(mockOidcLogin().idToken(token -> token.claim("name", "Mock User")
																  .claim("sub", "test-user")))
				.get()
				.uri("/whoami")
				.exchange()
				.expectBody()
				.jsonPath("$.userId").isEqualTo("test-user")
				.jsonPath("$.userName").isEqualTo("Mock User");
	}

	@Test
	void shouldReturnEmptyBodyWhenNoToken() {
		webTestClient
				.get()
				.uri("/whoami")
				.exchange()
				.expectBody().isEmpty();
	}

}
