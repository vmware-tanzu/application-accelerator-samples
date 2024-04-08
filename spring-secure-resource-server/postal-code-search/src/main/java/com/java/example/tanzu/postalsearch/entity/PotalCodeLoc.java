package com.java.example.tanzu.postalsearch.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("postalcodeloc")
public record PotalCodeLoc(@Id @Column("postalCode") String postalCode, Float latitude, Float longitude, boolean premium) 
{
}
