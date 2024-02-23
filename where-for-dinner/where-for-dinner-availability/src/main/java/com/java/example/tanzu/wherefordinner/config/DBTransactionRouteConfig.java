package com.java.example.tanzu.wherefordinner.config;

import java.util.Map;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.r2dbc.connection.lookup.AbstractRoutingConnectionFactory;
import org.springframework.util.StringUtils;

import io.r2dbc.pool.ConnectionPool;
import io.r2dbc.pool.ConnectionPoolConfiguration;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import io.r2dbc.spi.Option;
import lombok.extern.slf4j.Slf4j;
import io.r2dbc.spi.ConnectionFactoryOptions.Builder;
import reactor.core.publisher.Mono;


/**
 * Configures multiple data sources, one for RW transactions and one for RO transactions, and configures
 * routing to the appropriate data source based on the transaction context.  Enabled only if the "spring.r2dbc.ro.url"
 * property is set.
 * @author meyerg
 *
 */
@Configuration
@EnableConfigurationProperties(R2dbcProperties.class)
@ConditionalOnProperty("spring.r2dbc.ro.url")
@Slf4j
public class DBTransactionRouteConfig 
{

	/*
	 * Generates the connections propertes for the RW connection.
	 */
	@Bean
	@Primary
    @ConfigurationProperties(prefix = "spring.r2dbc")
	public R2dbcProperties rwR2dbcProperties()
	{
		return new R2dbcProperties();
	}
	
	/*
	 * Generates the connections propertes for the RO connection.
	 */
	@Bean
    @ConfigurationProperties(prefix = "spring.r2dbc.ro")	
	public R2dbcProperties roR2dbcProperties()
	{
		return new R2dbcProperties();
	}
	
	/*
	 * Generates the RW connection based on the RW connection properties
	 */
	@Bean
	@Primary
	public ConnectionFactory rwConnectionFactory() 
	{
		return createConnectionFactory(false);
	}		
	
	/*
	 * Generates the RO connection based on the RO connection properties
	 */
	@Bean
	public ConnectionFactory roConnectionFactory() 
	{
		log.info("Read only endpoint configuration detected.  Will create separate WR and RO connection factories.");
		
		return createConnectionFactory(true);
	}	
	
	/*
	 * Creates a pooling connection factory based on the RW or RO connection properties.
	 */
	protected ConnectionFactory createConnectionFactory(boolean ro)
	{
		final var props = (ro == true) ? roR2dbcProperties() : rwR2dbcProperties();
				
		
		final var urlOptions = ConnectionFactoryOptions.parse(props.getUrl());
		final var optionsBuilder = urlOptions.mutate();
		
		configureIf(optionsBuilder, urlOptions, ConnectionFactoryOptions.USER, props::getUsername,
				StringUtils::hasText);
		configureIf(optionsBuilder, urlOptions, ConnectionFactoryOptions.PASSWORD, props::getPassword,
				StringUtils::hasText);
		configureIf(optionsBuilder, urlOptions, ConnectionFactoryOptions.DATABASE,
				() -> determineDatabaseName(props), StringUtils::hasText);
		if (props.getProperties() != null) {
			props.getProperties()
				.forEach((key, value) -> optionsBuilder.option(Option.valueOf(key), value));
		}
		
		final var poolOpts = props.getPool();
		
		final var config = ConnectionPoolConfiguration
				.builder(ConnectionFactories.get(optionsBuilder.build()))
		        .maxIdleTime(poolOpts.getMaxIdleTime())
		        .minIdle(poolOpts.getMinIdle())
		        .initialSize(poolOpts.getInitialSize())
		        .maxSize(poolOpts.getMaxSize())
		        .maxAcquireTime(poolOpts.getMaxAcquireTime())
		        .validationQuery(StringUtils.hasText(poolOpts.getValidationQuery()) ? poolOpts.getValidationQuery() : "")
		        .build();
		
		return new ConnectionPool(config);

	}
	
	/*
	 * Creates a connection factory router to route transactions to the correct connection factory based on 
	 * the context "TYPE" key.  If no key exists, defaults to the RW connection.
	 */
	@Bean
	public AbstractRoutingConnectionFactory router(@Qualifier("rwConnectionFactory") ConnectionFactory rwConnectionFactory, 
			@Qualifier("roConnectionFactory") ConnectionFactory roConnectionFactory) 
	{

		AbstractRoutingConnectionFactory router = new AbstractRoutingConnectionFactory() {
			@Override
			protected Mono<Object> determineCurrentLookupKey() {
				return Mono.deferContextual(contextView -> {

					if (contextView.hasKey("TYPE")) {
						return Mono.just(contextView.get("TYPE"));
					}

					return Mono.empty();

				});
			}
		};

		router.setTargetConnectionFactories(Map.of("RW", rwConnectionFactory, "RO", roConnectionFactory));
		router.setDefaultTargetConnectionFactory(rwConnectionFactory);
		
		return router;
	}

	@Bean(name="r2dbcEntityTemplate")
	@Primary
	public R2dbcEntityTemplate r2dbcEntityTemplate(AbstractRoutingConnectionFactory router)
	{
		return new R2dbcEntityTemplate(router);
	}
	
	/*
	 * Creates a transaction manager that uses the customer connection router
	 */
	@Bean
	@Primary
	public R2dbcTransactionManager connectionFactoryTransactionManager(AbstractRoutingConnectionFactory router) 
	{
		return new R2dbcTransactionManager(router);
	}	
	
	/*
	 *  Helper functions below copied from non-visible R2DBC auto config methods.
	 */
	
	
	private <T extends CharSequence> void configureIf(Builder optionsBuilder,
			ConnectionFactoryOptions originalOptions, Option<T> option, Supplier<T> valueSupplier,
			Predicate<T> setIf) 
	{
		if (originalOptions.hasOption(option)) 
			return;

		T value = valueSupplier.get();
		if (setIf.test(value)) 
			optionsBuilder.option(option, value);
	}
	
	private String determineDatabaseName(R2dbcProperties properties) {
		if (properties.isGenerateUniqueName()) {
			return properties.determineUniqueName();
		}
		if (StringUtils.hasLength(properties.getName())) {
			return properties.getName();
		}
		return null;
	}
	
}
