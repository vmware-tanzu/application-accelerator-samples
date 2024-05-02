package com.java.example.tanzu.wherefordinner.processor.cache;

import java.time.Duration;

import reactor.core.publisher.Mono;

public interface HashCache 
{
	public Mono<String> getHashForKey(String key);
	
	public Mono<String> setHashForKey(String key, String value, Duration expiration);
	
	public Mono<Void> deleteHashForKey(String key);
}
