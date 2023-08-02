package com.java.example.tanzu.wherefordinner.model;

import java.util.List;

public record SearchCriteria (String name, long startTime, long endTime, List<String> diningTypes, List<String> diningNames, String postalCode, int radius)
{

}
