package com.java.example.tanzu.wherefordinner.function;

import java.time.Instant;
import java.util.Collections;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.java.example.tanzu.wherefordinner.SpringBaseTest;
import com.java.example.tanzu.wherefordinner.entity.Availability;
import com.java.example.tanzu.wherefordinner.entity.AvailabilityWindow;
import com.java.example.tanzu.wherefordinner.model.Search;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class AvailabilitySink_processDeletedSearchTest extends SpringBaseTest
{

	private Availability createAvailability(String searchName, String subject)
	{
		return new Availability(null, searchName, "testdining", "", "","", "", "", "", subject);
	}
	
	private AvailabilityWindow createAvailabilityWindow(Long availId)
	{
		return new AvailabilityWindow(availId, Instant.now().toEpochMilli(), Instant.now().toEpochMilli());
	}
	
	@Test
	public void testProcessDeletedSearch_assertAvailabilityDeleted() throws Exception
	{
	
		final var savedAvail = availRepo.save(createAvailability(UUID.randomUUID().toString(), UUID.randomUUID().toString())).block();
		
		final var savedWindow = availWinRepo.save(createAvailabilityWindow(savedAvail.getId())).block();
		
		/*
		 * Create and ensure they were saved
		 */
		availRepo.findById(savedAvail.getId())
		.as(StepVerifier::create) 
		.expectNext(savedAvail)
		.verifyComplete();
		
		availWinRepo.findByAvailabilityId(savedWindow.getAvailabilityId())
		.as(StepVerifier::create) 
		.expectNext(savedWindow)
		.verifyComplete();
		
		/*
		 * Delete
		 */
		sink.processDeletedSearch().
		    apply(Flux.fromIterable(Collections.singleton(new Search(savedAvail.getSearchName(), savedAvail.getRequestSubject()))))
		    .block();
		
		
		/*
		 * Verify deletion
		 */
		availRepo.findById(savedAvail.getId())
		.as(StepVerifier::create) 
		.expectNextCount(0)
		.verifyComplete();
		
		availWinRepo.findByAvailabilityId(savedAvail.getId())
		.as(StepVerifier::create) 
		.expectNextCount(0)
		.verifyComplete();
		
	}
	
	
}
