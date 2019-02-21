using System;

namespace Tech.Dominio
{
    public class EntityBase<Tid>
    {
        public Tid Id { get; set; }
        public DateTime DataCadastro { get; set; }
    }
}
