package com.vmware.identity;

import java.security.Principal;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Collections.emptyMap;

@RestController
public class WhoAmIController {

	private final Logger log = LoggerFactory.getLogger(WhoAmIController.class);

	@GetMapping("/whoami")
	public Map<String, String> getUserInfo(Principal principal) {
		log.debug("/whoami endpoint is triggered.");

		if (principal == null) {
			log.warn("Authentication is null cannot extract user info");
			return emptyMap();
		}

		return Map.of(
				"userId", principal.getName(),
				"userName", principal.getName());
	}

}
