using Microsoft.EntityFrameworkCore;
using RestService;
using RestService.Models;
using Steeltoe.Connector.PostgreSql;
using Steeltoe.Connector.PostgreSql.EFCore;
using Steeltoe.Extensions.Configuration.Kubernetes.ServiceBinding;
using Steeltoe.Management.Endpoint;

var builder = WebApplication.CreateBuilder(args);

builder.Configuration.AddKubernetesServiceBindings();
builder.Services.AddDbContext<CustomerProfilesContext>((services, options) => options.UseNpgsql(builder.Configuration));
builder.Services.AddPostgresHealthContributor(builder.Configuration);

builder.Services.AddControllers();
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

// Learn more about management endpoints at https://docs.steeltoe.io/api/v3/management/
builder.WebHost.AddAllActuators();

var app = builder.Build();

app.UseSwagger();
app.UseSwaggerUI();

app.UseHttpsRedirection();
app.MapControllers();

await PostgreSqlSeeder.CreateSampleDataAsync(app.Services);

app.Run();
