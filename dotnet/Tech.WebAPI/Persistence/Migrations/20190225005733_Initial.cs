using Microsoft.EntityFrameworkCore.Metadata;
using Microsoft.EntityFrameworkCore.Migrations;

namespace Tech.WebAPI.Migrations
{
    public partial class Initial : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "Alimentos",
                columns: table => new
                {
                    Id = table.Column<int>(nullable: false)
                        .Annotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn),
                    Nome = table.Column<string>(maxLength: 100, nullable: false),
                    Peso = table.Column<decimal>(nullable: true),
                    Caloria = table.Column<decimal>(nullable: true),
                    Carboidrato = table.Column<decimal>(nullable: true),
                    Proteina = table.Column<decimal>(nullable: true),
                    GorduraTotail = table.Column<decimal>(nullable: true),
                    GorduraSaturada = table.Column<decimal>(nullable: true),
                    FibraAlimentar = table.Column<decimal>(nullable: true),
                    Sodio = table.Column<decimal>(nullable: true),
                    Tag = table.Column<string>(maxLength: 255, nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Alimentos", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "Medidas",
                columns: table => new
                {
                    Id = table.Column<int>(nullable: false)
                        .Annotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn),
                    Nome = table.Column<string>(maxLength: 50, nullable: false),
                    Peso = table.Column<decimal>(nullable: false),
                    AlimentoId = table.Column<int>(nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Medidas", x => x.Id);
                    table.ForeignKey(
                        name: "FK_Medidas_Alimentos_AlimentoId",
                        column: x => x.AlimentoId,
                        principalTable: "Alimentos",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Restrict);
                });

            migrationBuilder.InsertData(
                table: "Medidas",
                columns: new[] { "Id", "AlimentoId", "Nome", "Peso" },
                values: new object[] { 1, null, "Seed Data", 18.3m });

            migrationBuilder.CreateIndex(
                name: "IX_Medidas_AlimentoId",
                table: "Medidas",
                column: "AlimentoId");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "Medidas");

            migrationBuilder.DropTable(
                name: "Alimentos");
        }
    }
}
