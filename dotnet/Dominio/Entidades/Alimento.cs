using System;
using System.Collections.Generic;
using Tech.Dominio.ValueObjects;

namespace Tech.Dominio.Entidades
{
    public class Alimento : EntityBase<Guid>
    {
        public Alimento()
        {
            Medidas = new HashSet<Medida>();
            Tags = new HashSet<Tag>();
        }

        public string Nome { get; set; }
        public decimal Peso { get; set; }
        public int Caloria { get; set; }
        public decimal Carboidrato { get; set; }
        public decimal Proteina { get; set; }
        public decimal GorduraTotail { get; set; }
        public decimal GorduraSaturada { get; set; }
        public decimal FibraAlimentar { get; set; }
        public decimal Sodio { get; set; }

        public IEnumerable<Tag> Tags { get; set; }
        public IEnumerable<Medida> Medidas { get; set; }
    }
}
