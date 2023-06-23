using Microsoft.Extensions.FileProviders;
using Steeltoe.Common.Hosting;
using Steeltoe.Management.Endpoint;

var builder = WebApplication.CreateBuilder(args);

builder.UseCloudHosting();

// Learn more about management endpoints at https://docs.steeltoe.io/api/v3/management/
builder.AddAllActuators();

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
}

app.UseSwagger();
app.UseSwaggerUI();

app.UseFileServer(new FileServerOptions
{
    FileProvider = new PhysicalFileProvider(Path.Combine(Directory.GetCurrentDirectory(), "StaticFiles")),
    RequestPath = "",
});

app.MapControllers();
app.Run();
