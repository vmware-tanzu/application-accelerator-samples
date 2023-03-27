package com.vmware.tanzu.apps.sso.accelerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class AppSSOAcceleratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppSSOAcceleratorApplication.class, args);
	}

}
