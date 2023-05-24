package com.vmware.tanzu.apps.sso.accelerator.config;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.security.oauth2.client.registration.ClientRegistrations;

/**
 * IssuerTrust
 *
 * Simple class to verify whether the registered clients' issuer is reachable using TLS.
 * If TLS connection fails, a custom descriptive error message is displayed with instructions
 * on how to rectify.
 *
 */
public class IssuerTrust {
	private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

	private final OAuth2ClientProperties properties;
	private final ApplicationContext context;

	public IssuerTrust(OAuth2ClientProperties properties, ApplicationContext context) {
		this.properties = properties;
		this.context = context;
	}

	@PostConstruct
	void verify() {
		for (var provider : properties.getProvider().values()) {
			try {
				ClientRegistrations.fromIssuerLocation(provider.getIssuerUri());
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
					SpringApplication.exit(context, () -> 1);
					System.exit(1);
				} else {
					throw e;
				}
			}
		}
	}
}
