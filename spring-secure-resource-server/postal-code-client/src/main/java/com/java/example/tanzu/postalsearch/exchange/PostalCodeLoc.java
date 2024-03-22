package com.java.example.tanzu.postalsearch.exchange;

public record PostalCodeLoc(String postalCode, Float latitude, Float longitude, boolean premium) 
{
}
