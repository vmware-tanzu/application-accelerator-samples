package com.java.example.tanzu.wherefordinner.model;

public record SearchCriteria (String name, long startTime, long endTime, String diningTypes, String diningNames, String postalCode, int radius, 
		String requestSubject, String sendResultsTo)
{

}
