package com.java.example.tanzu.wherefordinner.model;

import java.util.List;

import lombok.Data;

@Data
public class SearchCriteria 
{
	private String name;	
	
	private long startTime;
	
	private long endTime;
	
	private List<String> diningTypes;
	
	private List<String> diningNames;
	
	private String postalCode;
	
	private int radius;
}
