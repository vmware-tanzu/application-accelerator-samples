package com.java.example.tanzu.wherefordinner.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Availability 
{	
	private String searchName;

	private String diningName;

	private String address;
	
	private String locality;
	
	private String region;
	
	private String postalCode;
	
	private String phoneNumber;
	
	private String reservationURL;
	
	private String requestSubject;
	
	private List<AvailabilityWindow> availabilityWindows = new ArrayList<>();
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class AvailabilityWindow
	{
		private long startTime;
		
		private long endTime;
	}

}
