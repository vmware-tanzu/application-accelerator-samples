package com.java.example.tanzu.audit.function;

import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.java.example.tanzu.audit.model.AuditEvent;
import com.java.example.tanzu.audit.repository.AuditDataRepository;
import com.java.example.tanzu.audit.repository.AuditEventRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@Configuration
public class AuditSink {

	@Autowired
	protected AuditEventRepository auditEventRepo;
	
	@Autowired 
	protected AuditDataRepository auditDataRepo;
	
	@Bean
	public Function<Flux<AuditEvent>, Mono<Void>> processAuditEvent()
	{
		return events -> events
				.flatMap(event -> 
			{
		    	final var newEvent = new com.java.example.tanzu.audit.entity.AuditEvent(null, event.context(), event.name(), event.principal(), 
		    					event.result(), event.eventTime());
		    	
		    	return auditEventRepo.save(newEvent)
		    		.flatMap(savedEvent -> {
		    			
	        			final var datum = event.auditData().stream()
			        			  .map(data -> new com.java.example.tanzu.audit.entity.AuditData(null, data.name(), data.val(), savedEvent.id()))
			        			  .collect(Collectors.toList());
		    			
	        			return auditDataRepo.saveAll(datum).then();
		    		});		    					
			})
		    .onErrorContinue(Exception.class,  (e, o) ->
				System.out.println("Error processing audit event:" + e.getMessage()))					
			.then();
	}
	
}
