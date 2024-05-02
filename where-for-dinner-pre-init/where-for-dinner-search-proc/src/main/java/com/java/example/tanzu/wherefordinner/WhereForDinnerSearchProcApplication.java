package com.java.example.tanzu.wherefordinner;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class WhereForDinnerSearchProcApplication {

	public static void main(String[] args) 
	{
		new SpringApplicationBuilder(WhereForDinnerSearchProcApplication.class)
		 .run(args);
	}

}
