package com.java.example.tanzu.wherefordinner.function;

import java.util.ArrayList;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.java.example.tanzu.wherefordinner.model.Availability;
import com.java.example.tanzu.wherefordinner.repository.AvailabilityRepository;
import com.java.example.tanzu.wherefordinner.repository.AvailabilityWindowRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Configuration
@Slf4j
public class AvailabilitySink 
{
	@Autowired
	protected AvailabilityRepository availRepo;
	
	@Autowired
	protected AvailabilityWindowRepository availWinRepo;

	
	@Bean
	public Function<Flux<Availability>, Mono<Void>> processAvailability()
	{
		return avails -> avails.flatMap(avail -> 
			{
				log.info("Received availability for dining name {} in search {} for subject {}", avail.getDiningName(), avail.getSearchName(), avail.getRequestSubject());
				
				// check to see if this is an update or a new entry
				return availRepo.findBySearchNameAndDiningNameAndRequestSubject(avail.getSearchName(), avail.getDiningName(), avail.getRequestSubject())
					.switchIfEmpty(Mono.just(new com.java.example.tanzu.wherefordinner.entity.Availability(null, "", "", "", "","", "", "", "", "")))
					.flatMap(foundAvail -> 
					{
						// add a new availability entry if one does not already exist for this search/dining combo
						final Mono<com.java.example.tanzu.wherefordinner.entity.Availability> saveAvail = (foundAvail.getId() != null) ?
							Mono.just(foundAvail) : 
								availRepo.save(new com.java.example.tanzu.wherefordinner.entity.Availability(null, avail.getSearchName(), avail.getDiningName(), 
										avail.getAddress(), avail.getLocality(), avail.getRegion(), avail.getPostalCode(), avail.getPhoneNumber(), 
										avail.getReservationURL(), avail.getRequestSubject()));
						
						return saveAvail.flatMap(savedAvail ->
						{
							// delete any existing window entries and add new entries
							return availWinRepo.deleteByAvailabilityId(savedAvail.getId())
							  .then(saveTimeWindows(avail, savedAvail));

						});
					
					});
				
			}).then();
	}
	
	protected Mono<Void> saveTimeWindows(Availability avail, com.java.example.tanzu.wherefordinner.entity.Availability savedAvail)
	{
		  // no available windows... exit
		  if (avail.getAvailabilityWindows().isEmpty())
			  return Mono.empty();
		  
		  log.info("Saving {} windows for dining name {} in search {}", avail.getAvailabilityWindows().size(), avail.getDiningName(), avail.getSearchName());
		  
		  // save available windows
		  final var saveWindows = new ArrayList<com.java.example.tanzu.wherefordinner.entity.AvailabilityWindow>();
		  avail.getAvailabilityWindows().forEach(win ->  
             saveWindows.add(new com.java.example.tanzu.wherefordinner.entity.AvailabilityWindow(savedAvail.getId(), win.getStartTime(), win.getEndTime())));

		  return availWinRepo.saveAll(saveWindows).then();						  
		  
	}
}
