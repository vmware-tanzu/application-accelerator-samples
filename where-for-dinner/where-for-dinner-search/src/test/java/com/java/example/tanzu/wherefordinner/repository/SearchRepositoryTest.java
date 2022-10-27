package com.java.example.tanzu.wherefordinner.repository;

import java.util.Calendar;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import com.java.example.tanzu.wherefordinner.SpringBaseTest;
import com.java.example.tanzu.wherefordinner.entity.Search;

import reactor.test.StepVerifier;

@ActiveProfiles("local")
public class SearchRepositoryTest extends SpringBaseTest
{
	
	private Search createSearch()
	{
		var search = new Search();
		
		search.setEndTime(Calendar.getInstance().getTimeInMillis());
		search.setStartTime(Calendar.getInstance().getTimeInMillis());
		search.setName(UUID.randomUUID().toString());
		search.setRadius(10);
		search.setDiningNames(UUID.randomUUID().toString());
		search.setDiningTypes(UUID.randomUUID().toString());
		search.setPostalCode("64157");
		search.setRequestSubject(UUID.randomUUID().toString());
		
		return search;
	}
	
	private void addSearch(Search search) throws Exception
	{
		searchRepo.save(search)
		.as(StepVerifier::create) 
		.expectNextCount(1) 
		.verifyComplete();
	}
	
	@Test
	public void testGetAll() throws Exception
	{
		var search = createSearch();
		
		addSearch(search);
		
		var foundSearch = searchRepo.findAll().blockFirst();
		
		searchRepo.findAll()
		.as(StepVerifier::create)
		.expectNext(foundSearch)
		.verifyComplete();
		
	}
}
