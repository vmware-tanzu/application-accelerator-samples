package com.example.tanzu.streamtemplate.functions;

import java.util.Collections;
import java.util.UUID;
import java.util.function.Supplier;

import org.springframework.cloud.function.context.PollableBean;
import org.springframework.context.annotation.Configuration;

import com.example.tanzu.streamtemplate.model.Foo;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Configuration
@Slf4j
public class FooSource 
{
	@PollableBean
	public Supplier<Flux<Foo>> supplyFoo()
	{
		log.info("Supplier generating new {}",  Foo.class.getName());
		
		return () -> Flux.fromIterable(Collections.singletonList(new Foo("Test data: " + UUID.randomUUID().toString())));
	}
}
