using System;

namespace Tech.WebAPI.Domain.Exception
{
    [Serializable]
    public class AlimentoNotFoundException : System.Exception
    {
        public AlimentoNotFoundException()
            : base("Alimento não encontrado")
        { }
    }
}
