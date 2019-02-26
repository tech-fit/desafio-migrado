using Microsoft.EntityFrameworkCore;
using Tech.WebAPI.Domain;

namespace Tech.WebAPI.Persistence
{
    public class TechContext : DbContext
    {
        public TechContext()
        { }

        public TechContext(DbContextOptions options)
            : base(options)
        { }

        public DbSet<Alimento> Alimentos { get; set; }
        public DbSet<Medida> Medidas { get; set; }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.ApplyConfigurationsFromAssembly(typeof(TechContext).Assembly);
        }
    }
}
