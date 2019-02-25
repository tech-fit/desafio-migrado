namespace Tech.WebAPI.Domain
{
    public class Medida : EntityBase<int>
    {
        public string Nome { get; set; }
        public decimal Peso { get; set; }

        public Alimento Alimento { get; set; }
    }
}
