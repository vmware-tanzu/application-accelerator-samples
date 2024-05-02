package com.java.example.tanzu.wherefordinner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.client.WebClient;

import com.java.example.tanzu.wherefordinner.function.AvailabilitySink;
import com.java.example.tanzu.wherefordinner.repository.AvailabilityRepository;
import com.java.example.tanzu.wherefordinner.repository.AvailabilityWindowRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public abstract class SpringBaseTest 
{
	@Autowired
	protected AvailabilityRepository availRepo;
	
	@Autowired
	protected AvailabilityWindowRepository availWinRepo;
	
	@Autowired 
	protected AvailabilitySink sink;

	protected WebClient webClient = WebClient.builder().baseUrl("http://localhost:8080").build();
	
	@BeforeEach
	public void setUp()
	{	
		availRepo.deleteAll().block();
		
		availWinRepo.deleteAll().block();
	}
}
