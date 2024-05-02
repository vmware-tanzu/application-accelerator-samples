package com.java.example.tanzu.wherefordinner.searcher.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.java.example.tanzu.wherefordinner.config.StaticDiningAvailability;
import com.java.example.tanzu.wherefordinner.model.Availability;
import com.java.example.tanzu.wherefordinner.model.AvailabilityWindow;
import com.java.example.tanzu.wherefordinner.model.SearchCriteria;
import com.java.example.tanzu.wherefordinner.searcher.Searcher;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Component
@Slf4j
public class LocalRandomSearcher implements Searcher
{

	static final long HOUR = 3600 * 1000;
	
	static final long HALF_HOUR = HOUR / 2;

	@Autowired
	protected StaticDiningAvailability staticDining;
	
	@Override
	public Flux<Availability> search(SearchCriteria crit) 
	{		
		// if there is a list of dining names, we want to pick from that list
		final var diningFlux = createRandomDinings(crit);
		
		return diningFlux.map(dining -> createRandomAvailability(dining, crit))	;		
	}

	protected Flux<StaticDiningAvailability.Establishment> createRandomDinings(SearchCriteria crit)
	{
		log.debug("Creating random dining selections");
		
		List<StaticDiningAvailability.Establishment> bucket = null;
		var rn = new Random();
		var numDinings = 0;
		
		if (StringUtils.hasText(crit.diningNames()))
		{
			var dinNames = Arrays.asList(crit.diningNames().trim().split(","));
			
			var filteredDining = staticDining.getEstablishments().stream()
			.filter(estab -> isEstablishmentInList(estab.getDiningName(), dinNames))
			.collect(Collectors.toList());
			
			if (CollectionUtils.isEmpty(filteredDining))
				return Flux.empty();
			
			// decide how many we will find
			numDinings = rn.nextInt(filteredDining.size()) + 1;
		
			// create bucket to select from filtered the dining names
			bucket = new ArrayList<>(filteredDining);
		}
		else
		{
			// decide how many we will find
			numDinings = rn.nextInt(staticDining.getEstablishments().size()) + 1;
		
			// create bucket to select from using the dining names
			bucket = new ArrayList<>(staticDining.getEstablishments());
		}
		
		// create a random list of dining options
		final List<StaticDiningAvailability.Establishment> dinings = new ArrayList<>();
		
		// create our random list
		for (int i = 0; i < numDinings; ++i)
		{
			final var bucketEntryIdx = rn.nextInt(bucket.size());
			dinings.add(bucket.remove(bucketEntryIdx));
		}

		return Flux.fromIterable(dinings);
	}
	
	protected Availability createRandomAvailability(StaticDiningAvailability.Establishment dining, SearchCriteria crit)
	{
		log.debug("Creating random dining availability");
		
		var rn = new Random();
		
		// the chance that no reservations are available will be 20%
		final var isTimesAvailable = (rn.nextInt(10) > 1);
		
		final List<AvailabilityWindow> availWindows = new ArrayList<>();
		
		if (isTimesAvailable)
		{
			// create a random availability based on the search window
			final var times = new long[2];
			
			// select a random start time... the start time should not be less than an hour before the end time
			var windowSize = crit.endTime() - crit.startTime();
			
			// if the start and stop times are less than a 1 hour window, then just select
			// the start and end times as the window
			if (windowSize <= HOUR)
			{
				times[0] =  crit.startTime();
				times[1] = crit.endTime();
			}
			else
			{
				// find a random start time
				var max = crit.endTime() - HOUR;
				var min = crit.startTime();
				var startTime = min + (long) (Math.random() * (max - min));
				startTime = roundToHalfHour(startTime - HALF_HOUR);
				
				// find a random end time time
				max = crit.endTime();
				min = startTime + HALF_HOUR;
				var endTime = min + (long) (Math.random() * (max - min));
				endTime = roundToHalfHour(endTime - HALF_HOUR);
				
				times[0] = startTime;
				times[1] = endTime;	
			}
			
			availWindows.add(new AvailabilityWindow(times[0], times[1]));
		}
		
		return new Availability(crit.name(), dining.getDiningName(), dining.getAddress(), dining.getLocality(), dining.getRegion(), dining.getPostalCode(), dining.getPhoneNumber(),
				dining.getReservationURL(), crit.requestSubject(), crit.sendResultsTo(), availWindows);


	}
	
	protected long roundToHalfHour(long time)
	{
		if (time % HALF_HOUR == 0)
			return time;
		
		return (HALF_HOUR - (time % HALF_HOUR)) + time;
	}
	
	protected boolean isEstablishmentInList(String estabName, List<String> names)
	{
		for (String name : names)
			if (estabName.compareToIgnoreCase(name.trim()) == 0)
				return true;
		
		return false;
	}
}
