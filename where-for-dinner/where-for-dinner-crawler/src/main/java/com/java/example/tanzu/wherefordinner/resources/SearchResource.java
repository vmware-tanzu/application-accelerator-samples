package com.java.example.tanzu.wherefordinner.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.example.tanzu.wherefordinner.model.Availability;
import com.java.example.tanzu.wherefordinner.model.SearchCriteria;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("search")
public class SearchResource 
{
	@Autowired
	protected LocalRandomSearcher randomSearcher;
	
	@GetMapping
	public Flux<Availability> getSearch(@RequestParam(name="diningNames", required=false) List<String> diningNames, 
			@RequestParam(name="diningTypes", required=false) List<String> diningTypes, 
			@RequestParam(name="startTime", required=false) Long startTime, @RequestParam(name="endTime", required=false) Long endTime)
	{
		final var crit = new SearchCriteria();
		crit.setDiningNames(diningNames);
		crit.setDiningTypes(diningTypes);
		crit.setStartTime(startTime);
		crit.setEndTime(endTime);
		
		return randomSearcher.search(crit);
	}
}
