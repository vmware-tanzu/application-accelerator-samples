package com.java.example.tanzu.hungryman.publisher;

import com.java.example.tanzu.hungryman.model.Availability;

import reactor.core.publisher.Mono;

public interface Publisher 
{
	public Mono<Void> publishAvailability(Availability avail);
}
