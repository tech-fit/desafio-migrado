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

            builder.HasData(
                new Medida { Id = 3, Nome = "Escumadeira", Peso = 30 },
                new Medida { Id = 4, Nome = "Xicara", Peso = 200 },
                new Medida { Id = 5, Nome = "Colher de sopa", Peso = 25 },
                new Medida { Id = 6, Nome = "Unidade", Peso = 40 },
                new Medida { Id = 7, Nome = "Fatia", Peso = 15 }
                );
        }
    }
}
