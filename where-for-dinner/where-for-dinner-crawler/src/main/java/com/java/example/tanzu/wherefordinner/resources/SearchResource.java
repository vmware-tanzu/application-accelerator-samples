package com.java.example.tanzu.wherefordinner.resources;

import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.example.tanzu.wherefordinner.model.Availability;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("search")
public class SearchResource 
{
	@GetMapping
	public Flux<Availability> getSearch(@RequestParam(name="diningNames", required=false) List<String> diningNames, 
			@RequestParam(name="diningTypes", required=false) List<String> diningTypes)
	{
		var avail = new Availability();
		
		avail.setDiningName("Test Dining");

		return Flux.fromIterable(Collections.singleton(avail));
	}
}
