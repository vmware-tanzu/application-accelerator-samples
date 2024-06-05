package com.java.example.tanzu.postalsearch;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {"spring.shell.interactive.enabled=false"})
class PostalCodeClientApplicationTests {

	@BeforeAll
	public static void setUp() {
		System.setProperty("POSTAL_CODE_SERVICE_URL", "http://localhost:8080");
	}

	@Test
	void contextLoads() {
	}

}
