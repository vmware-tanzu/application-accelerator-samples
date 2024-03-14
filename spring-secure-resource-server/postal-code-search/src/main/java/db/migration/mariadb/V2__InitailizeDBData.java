package db.migration.mariadb;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

import db.migration.PostalCodeImport;


public class V2__InitailizeDBData extends BaseJavaMigration
{
	@Override
	public void migrate(Context context) throws Exception 
	{
		PostalCodeImport.importPostalCodes(context);
	}

}
