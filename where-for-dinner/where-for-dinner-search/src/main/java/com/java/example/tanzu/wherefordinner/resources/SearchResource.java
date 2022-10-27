package com.java.example.tanzu.wherefordinner.resources;


import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.java.example.tanzu.wherefordinner.entity.Search;
import com.java.example.tanzu.wherefordinner.repository.SearchRepository;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@OpenAPIDefinition(
        info = @Info(
                title = "Where for Dinner Search Service",
        		description = "Core Where for Dinner service for submitting searches and parameters"),
        tags = @Tag(
                name = "Search Submission REST API",
                description = "CRUD operations for submiting, retrieving, and deleting searches"))
@CrossOrigin
@RestController
@RequestMapping("search")
@Slf4j
public class SearchResource 
{
	protected static final String UNKNOWN_REQUEST_SUBJECT_ID = "ffffffff-ffff-ffff-ffff-ffffffffffff";
	
	protected static final String STREAM_BRIDGE_OUTPUT_CHANNLE = "submittedSearches-out-0";
	
	protected SearchRepository searchRepo;
	
	protected StreamBridge streamBridge;
	
	@Autowired
	public void setSearchRepo(SearchRepository searchRepo)
	{
		this.searchRepo = searchRepo;
	}
	
	@Autowired
	public void setStreamBridge(StreamBridge streamBridge)
	{
		this.streamBridge = streamBridge;
	}
	
	protected String getPrincipalName(Principal oauth2User)
	{	
		if (oauth2User != null)
		{
			return oauth2User.getName();
		}
		
		return UNKNOWN_REQUEST_SUBJECT_ID;
	}
	
	@Operation(summary = "Get all submitted searches", 
			description = "Returns all submitted searches for a given logged on user.  If a logged on user"
					+ " is not present, then a default principal representing non logged on users is applied.")
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Returns all searches for a user or an empty list if no search are found."
        )
    })
	@GetMapping
	public Flux<Search> getAllSearches( Principal oauth2User)
	{
		final var reqSub = getPrincipalName(oauth2User);
		
		return searchRepo.findByRequestSubject(reqSub)
    	   .onErrorResume(e -> { 
    	    	log.error("Error getting searches.", e);
    	    	return Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));
    	   });
	}
	
	@Operation(summary = "Submits a new search.", 
			description = "Submits a new search with a set of search parameters for a logged on user.  If a logged on user"
					+ " is not present, then a default principal representing non logged on users is applied.")
    @ApiResponses({
        @ApiResponse(
                responseCode = "201",
                description = "The search was successfully submitted"
        ),
        @ApiResponse(
                responseCode = "400",
                description = "The search time window has already passed",
                content = @Content(schema = @Schema(hidden = true))
        ),
        @ApiResponse(
                responseCode = "409",
                description = "A search with the same name for the calling user already exists",
                content = @Content(schema = @Schema(hidden = true))
        )
    })
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)  
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Search> addSearch(@RequestBody Search search, Principal oauth2User)
	{
		log.info("Adding new search {} in zip code {} and radius {}", search.getName(), search.getPostalCode(), search.getRadius());
	
		final var reqSub = getPrincipalName(oauth2User);
		
		final var curTime = System.currentTimeMillis();
		
		// If the requested end time has passed, then return a bad request
		if (search.getEndTime() < curTime)
		{
			log.error("The search end time has already passed.");
			return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST));
		}
		
		search.setRequestSubject(reqSub);
		
		return searchRepo.findByNameIgnoreCaseAndRequestSubject(search.getName(), reqSub)
		.switchIfEmpty(Mono.just(new Search()))
		.flatMap(foundSearch -> 
		{
			if (StringUtils.hasText(foundSearch.getName()))
			{
				log.error("Search name {} already exists.", search.getName());
				return Mono.error(new ResponseStatusException(HttpStatus.CONFLICT));
			}
				
			return searchRepo.save(search)
			           .doOnSuccess(addedSearch -> streamBridge.send(STREAM_BRIDGE_OUTPUT_CHANNLE, addedSearch))
			    	   .onErrorResume(e -> { 
			    	    	log.error("Error adding search.", e);
			    	    	return Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));
			    	   });
		});
    	   
	}
	
	@Operation(summary = "Deletes submitted search.", 
			description = "Deletes an existing submitted search.  If a logged on user"
					+ " is not present, then a default principal representing non logged on users is applied.")	
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "The search was successfully deleted"
        )
    })
	@DeleteMapping("{id}") 
	public Mono<Void> deleteSearch(@PathVariable("id") Long id)
	{
		log.info("Deleting search id {}", id);
		
		return searchRepo.deleteById(id)
    	   .onErrorResume(e -> { 
    	    	log.error("Error deleting search.", e);
    	    	return Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));
    	   });
	}
}
