using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using Tech.WebAPI.Domain;

namespace Tech.WebAPI.Persistence.Configuration
{
    public class AlimentoConfiguration : IEntityTypeConfiguration<Alimento>
    {
        public void Configure(EntityTypeBuilder<Alimento> builder)
        {
            builder.HasKey(x => x.Id);

            builder.Property(x => x.Id)
                .ValueGeneratedOnAdd();

            builder.Property(x => x.Nome)
                .IsRequired()
                .HasMaxLength(100);

            builder.Property(x => x.Tag)
                .HasMaxLength(255);

            builder.HasMany(x => x.Medidas).WithOne(x => x.Alimento);
        }
    }
}
