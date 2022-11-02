package com.java.example.tanzu.wherefordinner.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Table
@Data
@AllArgsConstructor
public class Availability 
{
	@Id
	private Long id;
	
	@NonNull
	@Column("searchName")
	private String searchName;
	
	@NonNull
	@Column("diningName")
	private String diningName;
	
	private String address;
	
	private String locality;
	
	private String region;
	
	@Column("postalCode")
	private String postalCode;
	
	@Column("phoneNumber")
	private String phoneNumber;
	
	@Column("reservationURL")
	private String reservationURL;
	
	@Column("requestSubject")
	private String requestSubject;
}
