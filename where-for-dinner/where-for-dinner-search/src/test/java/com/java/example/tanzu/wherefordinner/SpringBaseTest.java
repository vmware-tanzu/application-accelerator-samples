package com.java.example.tanzu.wherefordinner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.client.WebClient;

import com.java.example.tanzu.wherefordinner.repository.SearchRepository;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public abstract class SpringBaseTest 
{
	@Autowired
	protected SearchRepository searchRepo;

	protected WebClient webClient = WebClient.builder().baseUrl("http://localhost:8080").build();
	
	@BeforeEach
	public void setUp()
	{	
		searchRepo.deleteAll().block();
	}
}
