package com.java.example.tanzu.audit;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "spring.security.oauth2.resourceserver.jwt.issuer-uri=http://doesntmatter")
class AuditApplicationTests {

	@Test
	void contextLoads() {
	}

}
