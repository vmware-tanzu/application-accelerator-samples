package com.java.example.tanzu.wherefordinner.model;

import java.util.List;

public record Availability (String searchName, String diningName, String address, String locality, String region, String postalCode, String phoneNumber,
		String reservationURL, String requestSubject, List<AvailabilityWindow> availabilityWindows)
{	

}
