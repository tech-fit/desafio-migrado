using System;

namespace Tech.WebAPI.Models
{
    [Serializable]
    public class NovoAlimentoViewModel
    {

        public string Nome { get; set; }
        public decimal? Peso { get; set; }
        public decimal? Caloria { get; set; }
        public decimal? Carboidrato { get; set; }
        public decimal? Proteina { get; set; }
        public decimal? GorduraTotal { get; set; }
        public decimal? GorduraSaturada { get; set; }
        public decimal? FibraAlimentar { get; set; }
        public decimal? Sodio { get; set; }
        public string Tag { get; set; }
    }
}
