package com.vmware.tanzu.apps.sso.accelerator.web;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping("/")
	public String root() {
		return "redirect:/home";
	}

	@GetMapping("/home")
	public String home(Authentication authentication) {
		// When the user is already authenticated, do not show them the login page,
		// instead redirect to the "authenticated" home page
		if (authentication != null && authentication.isAuthenticated()) {
			return "redirect:/authenticated/home";
		}
		return "home";
	}
}
