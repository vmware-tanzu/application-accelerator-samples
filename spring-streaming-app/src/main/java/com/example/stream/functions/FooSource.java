package com.example.stream.functions;

import java.util.Collections;
import java.util.UUID;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.cloud.function.context.PollableBean;
import org.springframework.context.annotation.Configuration;

import com.example.stream.model.Foo;

import reactor.core.publisher.Flux;

@Configuration
public class FooSource 
{

	Logger logger = LoggerFactory.getLogger(FooSource.class);
  
	@PollableBean
	public Supplier<Flux<Foo>> supplyFoo()
	{
		return () -> {

			logger.info("Supplier generating new {}",  Foo.class.getName());
			return Flux.fromIterable(Collections.singletonList(new Foo("Test data: " + UUID.randomUUID().toString())));
		};
	}
}
