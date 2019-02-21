namespace Tech.Dominio.Entidades
{
    public class Medida : EntityBase<int>
    {
        public string Nome { get; set; }
        public decimal Peso { get; set; }
    }
}
