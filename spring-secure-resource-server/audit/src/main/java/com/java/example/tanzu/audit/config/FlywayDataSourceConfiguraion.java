package com.java.example.tanzu.audit.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.zaxxer.hikari.HikariDataSource;

/**
 * When R2DBC connection factories are present, Spring Boot autoconfiguration suppresses the 
 * creation of DataSource objects.  This configuration will manually create a data source object
 * to be used by FlyWay to manage database schemas.
 * @author Greg Meyer
 *
 * @since 1.0
 */
@Configuration
public class FlywayDataSourceConfiguraion 
{
	/*
	 * This is needed because the inclusion of the R2DBC connection factory auto configuration
	 * prevents the DataSource auto configuration from creating a DataSource object.  We need to create
	 * the data source bean manually 
	 */
	@Bean
	@Primary
	public HikariDataSource flywayDataSource(DataSourceProperties dataSourceProps) 
	{		
		return (HikariDataSource) dataSourceProps.initializeDataSourceBuilder()
				.type(HikariDataSource.class).build();
	}
}
