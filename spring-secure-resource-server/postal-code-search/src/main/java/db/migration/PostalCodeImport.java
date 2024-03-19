package db.migration;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;

import org.flywaydb.core.api.migration.Context;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class PostalCodeImport {

	private static final String COMMA_DELIMITER = ",";
	
	public static void importPostalCodes(Context context) throws Exception 
	{
		final Resource res = new ClassPathResource("data/usPostalCodeToGeoMapping.csv");
		
		/*
		 * Simple CSV reading to remove the need of pulling in external dependencies.
		 */
		try (BufferedReader br = new BufferedReader(new InputStreamReader(res.getInputStream()))) 
		{
			long cnt = 0;
		    String line;
		    while ((line = br.readLine()) != null) 
		    {
		        final var values = line.split(COMMA_DELIMITER);
		        
		        final var insert = "insert into postalcodeloc (postalCode, latitude, longitude, premium) values (?, ?, ?, ?)";
		        
				try(PreparedStatement statement = context.getConnection().prepareStatement(insert))
				{					
					statement.setString(1, values[0].strip());
					statement.setFloat(2, Float.parseFloat(values[1]));
					statement.setFloat(3, Float.parseFloat(values[2]));
					
					// make every other postal code a premium postal code
					statement.setBoolean(4, (cnt++ % 2) == 0);
					
					statement.execute();
				}
		    }
		}
	}
	
}
