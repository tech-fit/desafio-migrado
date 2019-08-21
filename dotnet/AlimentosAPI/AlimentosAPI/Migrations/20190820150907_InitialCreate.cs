using System;
using Microsoft.EntityFrameworkCore.Metadata;
using Microsoft.EntityFrameworkCore.Migrations;

namespace AlimentosAPI.Migrations
{
    public partial class InitialCreate : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "Alimentos",
                columns: table => new
                {
                    Nome = table.Column<string>(maxLength: 255, nullable: false),
                    IsAtivo = table.Column<bool>(nullable: false, defaultValue: false),
                    DataCriacao = table.Column<DateTime>(nullable: false),
                    DataAlteracao = table.Column<DateTime>(nullable: false),
                    Id = table.Column<int>(nullable: false)
                        .Annotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn),
                    QuantidadeGramas = table.Column<int>(nullable: false),
                    Calorias = table.Column<int>(nullable: false),
                    Carboidratos = table.Column<double>(nullable: false),
                    Proteinas = table.Column<double>(nullable: false),
                    GordurasTotais = table.Column<double>(nullable: false),
                    GordurasSaturadas = table.Column<double>(nullable: false),
                    FibraAlimentar = table.Column<double>(nullable: false),
                    Sodio = table.Column<int>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Alimentos", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "Medidas",
                columns: table => new
                {
                    Nome = table.Column<string>(maxLength: 255, nullable: false),
                    IsAtivo = table.Column<bool>(nullable: false, defaultValue: false),
                    DataCriacao = table.Column<DateTime>(nullable: false),
                    DataAlteracao = table.Column<DateTime>(nullable: false),
                    Id = table.Column<int>(nullable: false)
                        .Annotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Medidas", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "Tags",
                columns: table => new
                {
                    Nome = table.Column<string>(maxLength: 255, nullable: false),
                    IsAtivo = table.Column<bool>(nullable: false, defaultValue: false),
                    DataCriacao = table.Column<DateTime>(nullable: false),
                    DataAlteracao = table.Column<DateTime>(nullable: false),
                    Id = table.Column<int>(nullable: false)
                        .Annotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Tags", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "AlimentosMedidas",
                columns: table => new
                {
                    Id = table.Column<int>(nullable: false)
                        .Annotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn),
                    Quantidade = table.Column<double>(nullable: false),
                    MedidaId = table.Column<int>(nullable: false),
                    AlimentoId = table.Column<int>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_AlimentosMedidas", x => x.Id);
                    table.ForeignKey(
                        name: "FK_AlimentosMedidas_Alimentos_AlimentoId",
                        column: x => x.AlimentoId,
                        principalTable: "Alimentos",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_AlimentosMedidas_Medidas_MedidaId",
                        column: x => x.MedidaId,
                        principalTable: "Medidas",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "AlimentosTags",
                columns: table => new
                {
                    Id = table.Column<int>(nullable: false)
                        .Annotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn),
                    TagId = table.Column<int>(nullable: false),
                    AlimentoId = table.Column<int>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_AlimentosTags", x => x.Id);
                    table.ForeignKey(
                        name: "FK_AlimentosTags_Alimentos_AlimentoId",
                        column: x => x.AlimentoId,
                        principalTable: "Alimentos",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_AlimentosTags_Tags_TagId",
                        column: x => x.TagId,
                        principalTable: "Tags",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateIndex(
                name: "IX_AlimentosMedidas_AlimentoId",
                table: "AlimentosMedidas",
                column: "AlimentoId");

            migrationBuilder.CreateIndex(
                name: "IX_AlimentosMedidas_MedidaId",
                table: "AlimentosMedidas",
                column: "MedidaId");

            migrationBuilder.CreateIndex(
                name: "IX_AlimentosTags_AlimentoId",
                table: "AlimentosTags",
                column: "AlimentoId");

            migrationBuilder.CreateIndex(
                name: "IX_AlimentosTags_TagId",
                table: "AlimentosTags",
                column: "TagId");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "AlimentosMedidas");

            migrationBuilder.DropTable(
                name: "AlimentosTags");

            migrationBuilder.DropTable(
                name: "Medidas");

            migrationBuilder.DropTable(
                name: "Alimentos");

            migrationBuilder.DropTable(
                name: "Tags");
        }
    }
}
