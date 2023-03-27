package com.vmware.tanzu.apps.sso.accelerator.config;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientPropertiesRegistrationAdapter;

/**
 * IssuerTrust
 *
 * Simple class to verify whether the registered clients' issuer is reachable using TLS.
 * If TLS connection fails, a custom descriptive error message is displayed with instructions
 * on how to rectify.
 */
public class IssuerTrust {
	private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

	private final OAuth2ClientProperties properties;

	public IssuerTrust(OAuth2ClientProperties properties) {
		this.properties = properties;
	}

	@PostConstruct
	void verify() {
		try {
			OAuth2ClientPropertiesRegistrationAdapter.getClientRegistrations(properties);
		} catch (IllegalArgumentException e) {
			if (e.getMessage().contains("Unable to resolve Configuration with the provided Issuer") &&
					e.getCause().getMessage().contains("unable to find valid certification path to requested target")) {
				logger.error("\n" + """
						############################################################
						Establishing a TLS connection to the AuthServer has failed!
						
						The AuthServer may be serving a TLS certificate issued by a
						certificate authority that is not trusted by this application.
						Usually, that is the case when the issuing certificate authority
						is not public.
						
						Please refer to AppSSO docs for instructions on how to
						configure Workloads to trust AuthServers with custom CAs.
						
						The application cannot continue loading.
						############################################################""");
				System.exit(1);
			} else {
				throw e;
			}
		}
	}
}
