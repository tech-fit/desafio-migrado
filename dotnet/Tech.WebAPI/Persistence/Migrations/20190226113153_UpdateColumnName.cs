using Microsoft.EntityFrameworkCore.Migrations;

namespace Tech.WebAPI.Migrations
{
    public partial class UpdateColumnName : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DeleteData(
                table: "Medidas",
                keyColumn: "Id",
                keyValue: 1);

            migrationBuilder.RenameColumn(
                name: "GorduraTotail",
                table: "Alimentos",
                newName: "GorduraTotal");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.RenameColumn(
                name: "GorduraTotal",
                table: "Alimentos",
                newName: "GorduraTotail");

            migrationBuilder.InsertData(
                table: "Medidas",
                columns: new[] { "Id", "AlimentoId", "Nome", "Peso" },
                values: new object[] { 1, null, "Seed Data", 18.3m });
        }
    }
}
