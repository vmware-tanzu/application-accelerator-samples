using System;
using System.Collections.Generic;
using acme_order.Models;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Newtonsoft.Json;

namespace acme_order.Db
{
    public class SqliteOrderContext : OrderContext
    {
        public SqliteOrderContext(IConfiguration configuration)
            : base(configuration)
        {
        }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.UseSqlite(Configuration.GetConnectionString("OrderContext"));
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Order>(entity =>
            {
                entity.ToTable("order");

                entity.Property(e => e.Id)
                    .HasColumnName("id")
                    .ValueGeneratedOnAdd()
                    .IsRequired();

                entity.Property(e => e.Address)
                    .HasColumnName("address")
                    .HasConversion(
                        v => JsonConvert.SerializeObject(v,
                            new JsonSerializerSettings { NullValueHandling = NullValueHandling.Ignore }),
                        v => JsonConvert.DeserializeObject<Address>(v,
                            new JsonSerializerSettings { NullValueHandling = NullValueHandling.Ignore }));

                entity.Property(e => e.Card)
                    .HasColumnName("card")
                    .HasConversion(
                        v => JsonConvert.SerializeObject(v,
                            new JsonSerializerSettings { NullValueHandling = NullValueHandling.Ignore }),
                        v => JsonConvert.DeserializeObject<Card>(v,
                            new JsonSerializerSettings { NullValueHandling = NullValueHandling.Ignore }));

                entity.Property(e => e.Cart)
                    .HasColumnName("cart")
                    .HasConversion(
                        v => JsonConvert.SerializeObject(v,
                            new JsonSerializerSettings { NullValueHandling = NullValueHandling.Ignore }),
                        v => JsonConvert.DeserializeObject<ICollection<Cart>>(v,
                            new JsonSerializerSettings { NullValueHandling = NullValueHandling.Ignore }));

                entity.Property(e => e.Date)
                    .HasColumnName("date")
                    .HasDefaultValue(DateTime.UtcNow);

                entity.Property(e => e.Delivery)
                    .HasColumnName("delivery")
                    .HasMaxLength(1000);

                entity.Property(e => e.Email)
                    .HasColumnName("email")
                    .HasMaxLength(1000);

                entity.Property(e => e.Firstname)
                    .HasColumnName("firstname")
                    .HasMaxLength(1000);

                entity.Property(e => e.Lastname)
                    .HasColumnName("lastname")
                    .HasMaxLength(1000);

                entity.Property(e => e.Paid)
                    .HasColumnName("paid")
                    .HasMaxLength(1000);

                entity.Property(e => e.Total)
                    .HasColumnName("total")
                    .HasMaxLength(1000);

                entity.Property(e => e.UserId)
                    .HasColumnName("user_id")
                    .HasMaxLength(1000);
            });
        }
    }
}