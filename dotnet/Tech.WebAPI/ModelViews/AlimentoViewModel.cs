using System;
using System.Collections.Generic;
using Tech.WebAPI.Domain.ValueObjects;

namespace Tech.WebAPI.ModelViews
{
    [Serializable]
    public class AlimentoViewModel
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
    }
}
