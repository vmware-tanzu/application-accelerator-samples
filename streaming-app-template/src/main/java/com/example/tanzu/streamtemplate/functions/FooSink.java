package com.example.tanzu.streamtemplate.functions;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.tanzu.streamtemplate.model.Foo;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Configuration
@Slf4j
public class FooSink 
{
	@Bean
	public Function<Flux<Foo>, Mono<Void>> storeFoo()
	{
		return foos -> foos.flatMap(foo -> 
		{				
			log.info("Storing incoming {} in sink",  foo.getClass().getName());
			
			return Mono.empty();
		})
		.then();
	}
}
