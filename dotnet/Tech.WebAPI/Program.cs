using Microsoft.AspNetCore;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Server.Kestrel.Core;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.Logging;
using System;
using System.IO;
using System.Net;

namespace Tech.WebAPI
{
    public class Program
    {
        public static void Main(string[] args)
        {
            CreateWebHostBuilder(args)
                .UseStartup<Startup>()
                .Build()
                .Run();
        }

        // Configuração do webserver Kestrel retirada do link abaixo:
        //https://docs.microsoft.com/en-us/aspnet/core/fundamentals/servers/kestrel?view=aspnetcore-2.2
        public static IWebHostBuilder CreateWebHostBuilder(string[] args) =>
             WebHost
            .CreateDefaultBuilder()
            .ConfigureKestrel((context, options) =>
            {
                options.Limits.MaxConcurrentConnections = 700;
                options.Limits.MaxConcurrentUpgradedConnections = 700;
                options.Limits.MaxRequestBodySize = 500 * 1024;
                options.Limits.MinRequestBodyDataRate = new MinDataRate(bytesPerSecond: 100, gracePeriod: TimeSpan.FromSeconds(10));
                options.Limits.MinResponseDataRate = new MinDataRate(bytesPerSecond: 100, gracePeriod: TimeSpan.FromSeconds(10));
            })
            .UseStartup<Startup>();
    }
}
