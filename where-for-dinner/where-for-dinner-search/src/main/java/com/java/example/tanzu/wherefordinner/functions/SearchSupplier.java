package com.java.example.tanzu.wherefordinner.functions;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.function.context.PollableBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.reactive.TransactionalOperator;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.java.example.tanzu.wherefordinner.entity.Search;
import com.java.example.tanzu.wherefordinner.repository.SearchRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.util.context.Context;

@Configuration
@Slf4j
public class SearchSupplier
{
	@Autowired
	protected SearchRepository searchRepo;
	
	@Autowired
	protected ReactiveTransactionManager txMgr;
	
	@PollableBean
	public Supplier<Flux<Search>> submittedSearches()
	{
		return () ->
		{
			
			final var txDef = new DefaultTransactionDefinition();
			txDef.setReadOnly(true);
			final var transactionalOperator = TransactionalOperator.create(txMgr, txDef);
			
			final var curTime = System.currentTimeMillis();
			
			log.info("Gathering and sending all active searches to downstream processing");
			
			/*
			 * If the requested end time has passed, then don't search
			 */
			return searchRepo.findAll()	
				.filter(search -> search.getEndTime() >= curTime && search.isContinousSearch())
				.as(transactionalOperator::transactional).contextWrite(Context.of("TYPE", "RO"));
		};
	}
	
}
