using Microsoft.EntityFrameworkCore;

namespace RestService.Models;

public class CustomerProfilesContext : DbContext
{
    public CustomerProfilesContext(DbContextOptions options)
        : base(options)
    {
    }

    public DbSet<CustomerProfile> CustomerProfiles { get; set; } = null!;
}
