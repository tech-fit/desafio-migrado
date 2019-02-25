using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using Tech.WebAPI.Domain;

namespace Tech.WebAPI.Persistence.Configuration
{
    public class MedidaConfiguration : IEntityTypeConfiguration<Medida>
    {
        public void Configure(EntityTypeBuilder<Medida> builder)
        {
            builder.Property(x => x.Id)
                .ValueGeneratedOnAdd();

            builder.Property(x => x.Nome)
                .IsRequired()
                .HasMaxLength(50);

            builder.Property(x => x.Peso)
                .IsRequired();
        }
    }
}
