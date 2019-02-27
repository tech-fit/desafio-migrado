using Microsoft.EntityFrameworkCore.Migrations;

namespace Tech.WebAPI.Migrations
{
    public partial class seedMedidas : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.InsertData(
                table: "Medidas",
                columns: new[] { "Id", "AlimentoId", "Nome", "Peso" },
                values: new object[,]
                {
                    { 3, null, "Escumadeira", 30m },
                    { 4, null, "Xicara", 200m },
                    { 5, null, "Colher de sopa", 25m },
                    { 6, null, "Unidade", 40m },
                    { 7, null, "Fatia", 15m }
                });
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DeleteData(
                table: "Medidas",
                keyColumn: "Id",
                keyValue: 3);

            migrationBuilder.DeleteData(
                table: "Medidas",
                keyColumn: "Id",
                keyValue: 4);

            migrationBuilder.DeleteData(
                table: "Medidas",
                keyColumn: "Id",
                keyValue: 5);

            migrationBuilder.DeleteData(
                table: "Medidas",
                keyColumn: "Id",
                keyValue: 6);

            migrationBuilder.DeleteData(
                table: "Medidas",
                keyColumn: "Id",
                keyValue: 7);
        }
    }
}
