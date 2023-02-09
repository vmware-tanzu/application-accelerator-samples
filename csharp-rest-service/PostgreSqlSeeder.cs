using System.Data.Common;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Infrastructure;
using Microsoft.EntityFrameworkCore.Storage;
using RestService.Models;

namespace RestService;

internal sealed class PostgreSqlSeeder
{
    public static async Task CreateSampleDataAsync(IServiceProvider serviceProvider)
    {
        await using AsyncServiceScope scope = serviceProvider.CreateAsyncScope();

        try
        {
            await using var dbContext = scope.ServiceProvider.GetRequiredService<CustomerProfilesContext>();

            await DropCreateTablesAsync(dbContext);
            await InsertSampleDataAsync(dbContext);
        }
        catch (DbException exception)
        {
            var logger = serviceProvider.GetRequiredService<ILogger<PostgreSqlSeeder>>();
            logger.LogError(exception, "An error occurred seeding the DB.");
        }
    }

    private static async Task DropCreateTablesAsync(DbContext dbContext)
    {
        bool wasCreated = await dbContext.Database.EnsureCreatedAsync();

        if (!wasCreated)
        {
            // The database already existed. Because apps usually don't have permission to drop the database,
            // we drop and recreate all the tables in the DbContext instead.
            var databaseCreator = (RelationalDatabaseCreator)dbContext.Database.GetService<IDatabaseCreator>();

            await DropTablesAsync(dbContext);
            await databaseCreator.CreateTablesAsync();
        }
    }

    private static async Task DropTablesAsync(DbContext dbContext)
    {
        IEnumerable<string> tableNames = dbContext.Model.GetEntityTypes().Select(type => type.GetSchemaQualifiedTableName()!);
        IEnumerable<string> dropStatements = tableNames.Select(tableName => "DROP TABLE IF EXISTS \"" + tableName + "\";");

        string sqlStatement = string.Join(Environment.NewLine, dropStatements);
        await dbContext.Database.ExecuteSqlRawAsync(sqlStatement);
    }

    private static async Task InsertSampleDataAsync(CustomerProfilesContext dbContext)
    {
        dbContext.CustomerProfiles.AddRange(
            new CustomerProfile { FirstName = "John", LastName = "Doe", Email = "john@doe.com" },
            new CustomerProfile { FirstName = "Jane", LastName = "Doe", Email = "jane@doe.com" });

        await dbContext.SaveChangesAsync();
    }
}