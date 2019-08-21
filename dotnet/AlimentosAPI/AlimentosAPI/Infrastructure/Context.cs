using AlimentosAPI.Models;
using Microsoft.EntityFrameworkCore;

namespace AlimentosAPI.Infrastructure
{
    public class Context : DbContext
    {
        public Context(DbContextOptions options) : base(options)
        {
        }

        public DbSet<Alimento> Alimentos { get; set; }
        public DbSet<Medida> Medidas { get; set; }
        public DbSet<Tag> Tags { get; set; }
        public DbSet<AlimentoMedida> AlimentosMedidas { get; set; }
        public DbSet<AlimentoTag> AlimentosTags { get; set; }

        protected override void OnModelCreating(ModelBuilder builder)
        {
            base.OnModelCreating(builder);

            builder.Entity<Alimento>().Property(q => q.Nome).HasMaxLength(255).IsRequired();
            builder.Entity<Alimento>().Property(q => q.IsAtivo).HasDefaultValue(false);

            builder.Entity<Medida>().Property(q => q.Nome).HasMaxLength(255).IsRequired();
            builder.Entity<Medida>().Property(q => q.IsAtivo).HasDefaultValue(false);

            builder.Entity<Tag>().Property(q => q.Nome).HasMaxLength(255).IsRequired();
            builder.Entity<Tag>().Property(q => q.IsAtivo).HasDefaultValue(false);
        }
    }
}
