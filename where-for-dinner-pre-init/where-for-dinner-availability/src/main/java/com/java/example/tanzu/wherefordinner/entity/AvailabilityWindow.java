package com.java.example.tanzu.wherefordinner.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Table("availability_window")
@Data
@RequiredArgsConstructor
public class AvailabilityWindow 
{
	@Id
	private Long id;
	
	@NonNull
	@Column("availabilityId")
	private Long availabilityId;
	
	@NonNull
	@Column("startTime")
	private Long startTime;
	
	@NonNull
	@Column("endTime")
	private Long endTime;
}
