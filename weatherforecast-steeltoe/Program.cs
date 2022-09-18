using Microsoft.Extensions.FileProviders;
using Microsoft.OpenApi.Models;
using Steeltoe.Management.Endpoint;
using Steeltoe.Management.Tracing;

var builder = WebApplication.CreateBuilder(args);

// honor PORT var if set
var port = Environment.GetEnvironmentVariable("PORT") ?? "8080";
var url = string.Concat("http://0.0.0.0:", port);
builder.WebHost.UseUrls(url);

// Learn more about management endpoints at https://docs.steeltoe.io/api/v3/management/
builder.WebHost.AddAllActuators();

// Add services to the container.
builder.Services.AddControllers();

// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseDeveloperExceptionPage();
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseFileServer(new FileServerOptions
{
    FileProvider = new PhysicalFileProvider(Path.Combine(Directory.GetCurrentDirectory(), "StaticFiles")),
    RequestPath = "",
});

app.MapControllers();
app.Run();
