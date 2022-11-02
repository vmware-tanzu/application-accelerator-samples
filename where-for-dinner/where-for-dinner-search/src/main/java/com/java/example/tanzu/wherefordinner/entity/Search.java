package com.java.example.tanzu.wherefordinner.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Table
@Data
public class Search 
{
	@Id
	private Long id;

	private String name;	
	
	@Column("startTime")
	private long startTime;
	
	@Column("endTime")
	private long endTime;
	
	/*
	 * For data model simplicity, using a comma delimited set for dining types and dining names
	 */
	@Column("diningTypes")
	private String diningTypes;
	
	@Column("diningNames")
	private String diningNames;
	
	@Column("postalCode")
	private String postalCode;
	
	private int radius;
	
	@Column("continousSearch")
	private boolean continousSearch;
	
	@Column("requestSubject")
	private String requestSubject;
	
}
