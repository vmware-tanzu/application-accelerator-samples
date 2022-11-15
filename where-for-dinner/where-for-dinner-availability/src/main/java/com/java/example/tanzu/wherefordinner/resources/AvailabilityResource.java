	package com.java.example.tanzu.wherefordinner.resources;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.java.example.tanzu.wherefordinner.model.Availability;
import com.java.example.tanzu.wherefordinner.repository.AvailabilityRepository;
import com.java.example.tanzu.wherefordinner.repository.AvailabilityWindowRepository;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@OpenAPIDefinition(
        info = @Info(
                title = "Where for Dinner Availability Service",
        		description = "Core Where For Dinner service for retrieving dining availability"),
        tags = @Tag(
                name = "Availability REST API",
                description = "Operations for retrieving dining availability"))
@CrossOrigin
@RestController
@RequestMapping("availability")
@Slf4j
public class AvailabilityResource 
{
	@Value("${app.version:unknown}") 
	protected String appVersion;
	
	protected static final String UNKNOWN_REQUEST_SUBJECT_ID = "ffffffff-ffff-ffff-ffff-ffffffffffff";
	
	protected AvailabilityRepository availRepo;
	
	protected AvailabilityWindowRepository availWindowRepo;
	
	/*
	 * Using setters for unit testing purposes
	 */
	@Autowired
	public void setAvailabilityRepository(AvailabilityRepository availRepo)
	{
		this.availRepo = availRepo;
	}
	
	@Autowired
	public void setAvailabilityWindowRepository(AvailabilityWindowRepository availWindowRepo)
	{
		this.availWindowRepo = availWindowRepo;
	}
	
	protected String getPrincipalName(Principal oauth2User)
	{	
		if (oauth2User != null)
		{
			return oauth2User.getName();
		}
		
		return UNKNOWN_REQUEST_SUBJECT_ID;
	}
	
	@Operation(summary = "Get all availability across all searches", 
			description = "Returns all availability regardless of search name for a given logged on user.  If a logged on user"
					+ " is not present, then a default principal representing non logged on users is applied.")
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Returns all availability for a user or an empty list if no availability is found."
        )
    })
	@GetMapping
	public Flux<Availability> getAllAvailabilty(Principal oauth2User)
	{
		final var reqSub = getPrincipalName(oauth2User);
		
		return getAvailabilityFromFlux(availRepo.findByRequestSubject(reqSub));
	}
	
	@Operation(summary = "Get all availability for a given search name", 
			description = "Returns all availability for a search namd and logged on user.  If a logged on user"
					+ " is not present, then a default principal representing non logged on users is applied.")
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Returns all availability for a user and search name or an empty list if no availability is found."
        )
    })
	@GetMapping("{searchName}")
	public Flux<com.java.example.tanzu.wherefordinner.model.Availability> getSearchAvailabilty(@PathVariable("searchName") String searchName, Principal oauth2User)
	{
		final var reqSub = getPrincipalName(oauth2User);
		
		return getAvailabilityFromFlux(availRepo.findBySearchNameAndRequestSubject(searchName, reqSub));
	}
	
	protected Flux<Availability> getAvailabilityFromFlux(Flux<com.java.example.tanzu.wherefordinner.entity.Availability> flux)
	{
		return flux.flatMap(avail -> 
		   {
			   final var retAvail = new Availability();
			   retAvail.setSearchName(avail.getSearchName());
			   retAvail.setDiningName(avail.getDiningName());
			   retAvail.setAddress(avail.getAddress());
			   retAvail.setLocality(avail.getLocality());
			   retAvail.setPhoneNumber(avail.getPhoneNumber());
			   retAvail.setPostalCode(avail.getPostalCode());
			   retAvail.setRegion(avail.getRegion());
			   retAvail.setReservationURL(avail.getReservationURL());
			   
			   return availWindowRepo.findByAvailabilityId(avail.getId())
				  .switchIfEmpty(Mono.just(new com.java.example.tanzu.wherefordinner.entity.AvailabilityWindow(0L, 0L, 0L)))
				  .map(win ->
			      {
			    	 if (win.getId() != null)
			    	 {
				    	 var retWin = new Availability.AvailabilityWindow();
				    	 retWin.setStartTime(win.getStartTime());
				    	 retWin.setEndTime(win.getEndTime());
				    	 
				    	 retAvail.getAvailabilityWindows().add(retWin);
			    	 }
			    	 
				     return retAvail;
			      });
		   })
	 	   .onErrorResume(e -> { 
	 	    	log.error("Error getting availability.", e);
	 	    	return Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));
	 	   });
	}
	
	@Operation(summary = "Get app version", 
			description = "Returns the version of the application")
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Returns the version of the application"
        )
    })
	@GetMapping("app/version")
	public Mono<String> getAvailabilityAppVersion()
	{
		return Mono.just(appVersion);
	}
}
