package com.java.example.tanzu.wherefordinner.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Availability 
{	
	private String diningName;

	private String address;
	
	private String locality;
	
	private String region;
	
	private String postalCode;
	
	private String phoneNumber;
	
	private String reservationURL;
	
	private List<AvailabilityWindow> availabilityWindows = new ArrayList<>();
	
	@Data
	@AllArgsConstructor
	public static class AvailabilityWindow
	{
		private long startTime;
		
		private long endTime;
	}
}
