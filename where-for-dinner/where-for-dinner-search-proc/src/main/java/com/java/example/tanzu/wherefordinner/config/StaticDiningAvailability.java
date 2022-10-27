package com.java.example.tanzu.wherefordinner.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "where-for-dinner.randomsearcher") 
@Data
public class StaticDiningAvailability 
{
	private List<Establishment> establishments;
	
	@Data
	public static class Establishment
	{
		private String diningName;

		private String address;
		
		private String locality;
		
		private String region;
		
		private String postalCode;
		
		private String phoneNumber;
		
		private String reservationURL;
	}
}
