using System;
using System.Collections.Generic;
using Microsoft.EntityFrameworkCore.Migrations;
using acme_order.Models;

namespace acmeorder.Migrations.Postgres
{
    public partial class InitialPostgres : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AlterDatabase()
                .Annotation("Npgsql:PostgresExtension:uuid-ossp", ",,");

            migrationBuilder.CreateTable(
                name: "order",
                columns: table => new
                {
                    id = table.Column<Guid>(type: "uuid", nullable: false, defaultValueSql: "uuid_generate_v4()"),
                    date = table.Column<DateTime>(nullable: true, defaultValue: new DateTime(2022, 5, 9, 8, 55, 19, 197, DateTimeKind.Utc).AddTicks(5020)),
                    paid = table.Column<string>(maxLength: 1000, nullable: true),
                    user_id = table.Column<string>(maxLength: 1000, nullable: true),
                    firstname = table.Column<string>(maxLength: 1000, nullable: true),
                    lastname = table.Column<string>(maxLength: 1000, nullable: true),
                    email = table.Column<string>(maxLength: 1000, nullable: true),
                    delivery = table.Column<string>(maxLength: 1000, nullable: true),
                    total = table.Column<string>(maxLength: 1000, nullable: true),
                    address = table.Column<Address>(type: "json", nullable: true),
                    card = table.Column<Card>(type: "json", nullable: true),
                    cart = table.Column<ICollection<Cart>>(type: "json", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_order", x => x.id);
                });
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "order");
        }
    }
}
