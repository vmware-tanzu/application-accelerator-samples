package com.java.example.tanzu.wherefordinner.model;

import lombok.Data;

@Data
public class SearchCriteria 
{
	private String name;	
	
	private long startTime;
	
	private long endTime;
	
	private String diningTypes;
	
	private String diningNames;
	
	private String postalCode;
	
	private int radius;
	
	private String requestSubject;
}
