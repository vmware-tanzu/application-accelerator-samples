package com.java.example.tanzu.hungryman.processor.cache;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component
public class MemoryHashCache implements HashCache
{
	protected final Map<String, String> cache = new ConcurrentHashMap<>();
	
	
	@Override
	public Mono<String> getHashForKey(String key) 
	{
		return Mono.just(cache.getOrDefault(key, ""));
	}

	@Override
	public Mono<String> setHashForKey(String key, String value, Duration expiration) 
	{
		cache.put(key, value);
		return Mono.just(value);
	}

	@Override
	public Mono<Void> deleteHashForKey(String key) 
	{
		cache.remove(key);
		return Mono.empty();
	}

}
