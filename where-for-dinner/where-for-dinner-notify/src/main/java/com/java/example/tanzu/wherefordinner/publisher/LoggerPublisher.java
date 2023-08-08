package com.java.example.tanzu.wherefordinner.publisher;

import java.text.DateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Locale;

import com.java.example.tanzu.wherefordinner.model.Availability;
import com.java.example.tanzu.wherefordinner.model.AvailabilityWindow;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * A publisher that dumps clinic notification messages to the configured logger.
 * @author Greg Meyer
 *
 */
@Slf4j
public class LoggerPublisher implements Publisher
{
	public LoggerPublisher()
	{
		
	}

	@Override
	public Mono<Void> publishAvailability(Availability avail) 
	{
		log.info("Publishing dining availability for {} from search name {}", avail.diningName(), avail.searchName());
		
		if (avail.availabilityWindows().isEmpty())
			log.info("\tNo available dining times\r\n");
		else
		{
			final var msgBuilder = new StringBuilder();
			
			int cnt = 1;
			for (AvailabilityWindow window : avail.availabilityWindows())
			{
				final var formatter = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, Locale.getDefault());
				
				
				final var startTime = formatter.format(Date.from(Instant.ofEpochMilli(window.startTime())));
				final var endTime = formatter.format(Date.from(Instant.ofEpochMilli(window.endTime())));
				

				
				msgBuilder.append("\r\n\t").append(cnt).append(". Window Start Time: ").append(startTime);
				msgBuilder.append("\r\n\t").append(cnt).append(". Window End Time: ").append(endTime).append("\r\n");
				
				++cnt;
			}
			
			log.info(msgBuilder.toString());
		}	
		return Mono.empty();
	}

}
