using System.Collections.Generic;
using System.Linq;
using Tech.WebAPI.Domain;

namespace Tech.WebAPI.ModelViews
{
    public class MedidaViewModel
    {
        public string Nome { get; set; }
        public decimal Peso { get; set; }

        public static MedidaViewModel GetFrom(Medida medida)
        {
            return new MedidaViewModel() { Nome = medida.Nome, Peso = medida.Peso };
        }

        public static IEnumerable<MedidaViewModel> GetFrom(IEnumerable<Medida> medidas)
        {
            return medidas.Select(x => GetFrom(x));
        }
    }
}
