using acme_order.Models;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;

namespace acme_order.Db
{
    public abstract class OrderContext : DbContext
    {
        protected readonly IConfiguration Configuration;

        protected OrderContext(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public virtual DbSet<Order> Orders { get; set; }
    }
}