using AlimentosAPI.Domain.Services;
using AlimentosAPI.Domain.Services.Interfaces;
using AlimentosAPI.Infrastructure;
using AlimentosAPI.Models;
using AlimentosAPI.Repository;
using AlimentosAPI.Repository.Interfaces;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using System;

namespace AlimentosAPI
{
    public class Startup
    {
        private const string corsName = "default";

        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public IConfiguration Configuration { get; }

        private string ConfigureConnectionString()
            => Configuration.GetConnectionString("Default").Replace("?path?", Environment.CurrentDirectory);

        // This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services)
        {
            services.AddMvc().SetCompatibilityVersion(CompatibilityVersion.Version_2_1);
            services.AddDbContext<Context>(
                            option => option.UseSqlServer(ConfigureConnectionString()));

            services.AddCors(op =>
            {
                op.AddPolicy(corsName, builder =>
                {
                    builder.AllowAnyOrigin();
                    builder.AllowAnyMethod();
                    builder.AllowAnyHeader();
                });
            });

            services.AddTransient<IComumService<Alimento>, AlimentoService>();
            services.AddTransient<IComumService<Medida>, MedidaService>();
            services.AddTransient<IComumService<Tag>, TagService>();

            services.AddTransient<IComumRepository<Alimento>, AlimentoRepository>();
            services.AddTransient<IComumRepository<Medida>, MedidaRepository>();
            services.AddTransient<IComumRepository<Tag>, TagRepository>();
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IHostingEnvironment env)
        {
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
            }
            else
            {
                app.UseHsts();
            }

            app.UseHttpsRedirection();
            app.UseCors(corsName);
            app.UseMvc();
        }
    }
}
