using System;
using Microsoft.EntityFrameworkCore.Migrations;

namespace acmeorder.Migrations.Sqlite
{
    public partial class InitialSqlite : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "order",
                columns: table => new
                {
                    id = table.Column<Guid>(nullable: false),
                    date = table.Column<DateTime>(nullable: true, defaultValue: new DateTime(2022, 5, 9, 9, 17, 40, 879, DateTimeKind.Utc).AddTicks(7650)),
                    paid = table.Column<string>(maxLength: 1000, nullable: true),
                    user_id = table.Column<string>(maxLength: 1000, nullable: true),
                    firstname = table.Column<string>(maxLength: 1000, nullable: true),
                    lastname = table.Column<string>(maxLength: 1000, nullable: true),
                    email = table.Column<string>(maxLength: 1000, nullable: true),
                    delivery = table.Column<string>(maxLength: 1000, nullable: true),
                    total = table.Column<string>(maxLength: 1000, nullable: true),
                    address = table.Column<string>(nullable: true),
                    card = table.Column<string>(nullable: true),
                    cart = table.Column<string>(nullable: true)
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
