package com.example.stream.functions;

import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.stream.model.Foo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Configuration
public class FooSink 
{

	Logger logger = LoggerFactory.getLogger(FooSink.class);

	@Bean
	public Function<Flux<Foo>, Mono<Void>> storeFoo()
	{
		return foos -> foos.flatMap(foo -> 
		{				
			logger.info("Storing incoming {} in sink",  foo.getClass().getName());
			
			return Mono.empty();
		})
		.then();
	}
}
