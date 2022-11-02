package com.java.example.tanzu.wherefordinner.publisher;

import com.java.example.tanzu.wherefordinner.model.Availability;

import reactor.core.publisher.Mono;

public interface Publisher 
{
	public Mono<Void> publishAvailability(Availability avail);
}
