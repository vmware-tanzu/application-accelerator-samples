package com.java.example.tanzu.wherefordinner.processor.cache;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component
@Profile("redis")
public class RedisHashCache implements HashCache
{
	@Autowired
	protected ReactiveStringRedisTemplate redisTemplate;
	
	
	@Override
	public Mono<String> getHashForKey(String key) 
	{
		return redisTemplate.opsForValue()
		    .get(key)
			.switchIfEmpty(Mono.just("")); 
	}

	@Override
	public Mono<String> setHashForKey(String key, String value, Duration expiration) 
	{
		return redisTemplate.opsForValue()
			.set(key, value, expiration)
			.then(Mono.just(value));
	}

	@Override
	public Mono<Void> deleteHashForKey(String key) 
	{
		return redisTemplate.opsForValue()
			.delete(key)
			.then();
	}

}
