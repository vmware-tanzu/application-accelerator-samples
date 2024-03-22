package com.java.example.tanzu.postalsearch.commands;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import com.java.example.tanzu.postalsearch.exchange.PostalCodeSearchClient;


@ShellComponent
public class SearchCommand {

	protected PostalCodeSearchClient client;
	
    public SearchCommand(PostalCodeSearchClient client)
    {
        this.client = client;
    }
    
	@ShellMethod(value = "Search for the geo location of a postal code.", key = "searchPostalCode")
	public String searchPostalCode(@ShellOption(help="The postal code to search for.") String postalCode)
	{
		try
		{
			final var geoloc = client.search(postalCode).block();
			if (geoloc == null)
				return String.format("Postal code %s could not be found.", postalCode);
				
	        return String.format("Latitude: %f \r\nLatitude: %f", geoloc.latitude(), geoloc.longitude());
		}
		catch (Exception e)
		{
			return "Error looking up postal code: " + e.getMessage();
		}
	}
}
