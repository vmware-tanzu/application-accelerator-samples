package com.java.example.tanzu.audit.resources;

import java.security.Principal;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.java.example.tanzu.audit.model.AuditData;
import com.java.example.tanzu.audit.model.AuditEvent;
import com.java.example.tanzu.audit.repository.AuditDataRepository;
import com.java.example.tanzu.audit.repository.AuditEventRepository;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("audit")
public class AuditResource {

	protected final Logger log = LoggerFactory.getLogger(AuditResource.class);
	
	@Value("${auidit.api.roles.security-officert:ROLE_SECURITY_OFFICER}")
	protected String secOfficerRole;	
	
	@Autowired
	protected AuditEventRepository auditEventRepo;
	
	@Autowired
	protected AuditDataRepository auditDataRepo;	

	@PreAuthorize("hasAuthority(@environment.getProperty('auidit.api.authorized-role'))")
	@GetMapping(value="eventTime/{startTime}")
	public Mono<Page<com.java.example.tanzu.audit.model.AuditEvent>> getEventsByStartTime(@PathVariable("startTime") long startTime, 
			@RequestParam(value="page", required=false, defaultValue="0") int page, 
			@RequestParam(value="size", required=false, defaultValue="20") int size, Principal prin)
	{
		return getEventsByStartEndTime(startTime, -1, page, size, prin);
	}
	
	@PreAuthorize("hasAuthority(@environment.getProperty('auidit.api.authorized-role'))")
	@GetMapping(value="eventTime/{startTime}/{endTime}")
	public Mono<Page<com.java.example.tanzu.audit.model.AuditEvent>> getEventsByStartEndTime(@PathVariable("startTime") long startTime, 
			@PathVariable("endTime") long endTime,  @RequestParam(value="page", required=false, defaultValue="0") int page, 
			@RequestParam(value="size", required=false, defaultValue="20") int size, Principal prin)
	{
		final var pageAndSort = PageRequest.of(page, size, Sort.by("eventTime").ascending());
		
		final var events = (endTime > -1) ? auditEventRepo.findByEventTimeBetween(startTime, endTime, pageAndSort) :
			auditEventRepo.findByEventTimeGreaterThanEqual(startTime, pageAndSort);
		
		return events.collectList()
		.flatMapIterable(list -> list)
		.flatMap(event -> {
			
			return auditDataRepo.findByAuditId(event.id())
				.collectList()
				.map(data -> {
					
					final var dataList = data.stream()
							.map(datum -> new AuditData(datum.name(), datum.val()))
							.collect(Collectors.toList());
					
					return new AuditEvent(event.context(), event.name(), event.principal(), 
							event.result(), event.eventTime(), dataList);
					
				});			
		})
		.collectList()	
		.zipWith(auditEventRepo.count())	
 	    .onErrorResume(e -> { 
	    	log.error("Error getting audit data.", e);
	    	return Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));
	    })		
		.map(t -> new PageImpl<>(t.getT1(), pageAndSort, t.getT2()));
	
		
	}
}