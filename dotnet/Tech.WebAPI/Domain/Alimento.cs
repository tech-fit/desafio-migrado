using System;
using System.Collections.Generic;

namespace Tech.WebAPI.Domain
{
    public class Alimento : EntityBase<int>
    {
        public Alimento()
        {
            Medidas = new HashSet<Medida>();
        }

        public string Nome { get; set; }
        public decimal? Peso { get; set; }
        public decimal? Caloria { get; set; }
        public decimal? Carboidrato { get; set; }
        public decimal? Proteina { get; set; }
        public decimal? GorduraTotal { get; set; }
        public decimal? GorduraSaturada { get; set; }
        public decimal? FibraAlimentar { get; set; }
        public decimal? Sodio { get; set; }
        private string _tag;
        public string Tag
        {
            get { return _tag; }
            set { _tag = Convert.ToString(value).Trim(); }
        }


        public IEnumerable<Medida> Medidas { get; set; }

    }
}
