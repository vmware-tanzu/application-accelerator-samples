using acme_order.Auth;
using acme_order.Configuration;
using acme_order.Db;
using acme_order.Services;
using Microsoft.ApplicationInsights.Extensibility;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Options;

namespace acme_order
{
    public class Startup
    {
        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public IConfiguration Configuration { get; }

        // This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services)
        {
            services.Configure<AcmeServiceSettings>(
                Configuration.GetSection(nameof(AcmeServiceSettings)));

            services.AddSingleton<IAcmeServiceSettings>(sp =>
                sp.GetRequiredService<IOptions<AcmeServiceSettings>>().Value);

            switch (Configuration["DatabaseProvider"])
            {
                case "Sqlite":
                    services.AddDbContext<OrderContext, SqliteOrderContext>(ServiceLifetime.Singleton);
                    break;

                case "Postgres":
                    services.AddDbContext<OrderContext, PostgresOrderContext>(ServiceLifetime.Singleton);
                    break;
            }

            services.AddSingleton<OrderService>();
            services.AddControllers();
            services.AddScoped<AuthorizeResource>();

            services.AddApplicationInsightsTelemetry();
            services.AddSingleton<ITelemetryInitializer, CloudRoleNameTelemetryInitializer>();
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IWebHostEnvironment env)
        {
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
            }

            app.UseHttpsRedirection();
            app.UseRouting();
            app.UseEndpoints(endpoints => { endpoints.MapControllers(); });
        }
    }
}