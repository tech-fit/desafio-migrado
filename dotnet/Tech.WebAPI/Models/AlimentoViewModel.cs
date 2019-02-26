using System;
using Tech.WebAPI.Domain;

namespace Tech.WebAPI.Models
{
    [Serializable]
    public class AlimentoViewModel
    {
        public string Nome { get; set; }
        public string Peso { get; set; }
        public string Caloria { get; set; }
        public string Carboidrato { get; set; }
        public string Proteina { get; set; }
        public string GorduraTotal { get; set; }
        public string GorduraSaturada { get; set; }
        public string FibraAlimentar { get; set; }
        public string Sodio { get; set; }

        public AlimentoViewModel(Alimento alimento)
        {
            Nome = alimento.Nome;
            Peso = Format(alimento.Peso?.ToString("N1"), "{0} gramas");
            Caloria = Format(alimento.Caloria, "(valor energético) {0} kcal");
            Proteina = Format(alimento.Proteina?.ToString("N1"), "{0}g");
            GorduraTotal = Format(alimento.GorduraTotal?.ToString("N1"), "{0}g");
            GorduraSaturada = Format(alimento.GorduraSaturada?.ToString("N1"), "{0}g");
            FibraAlimentar = Format(alimento.FibraAlimentar?.ToString("N1"), "{0}g");
            Sodio = Format(alimento.Sodio?.ToString("N1"), "{0}mg");
        }

        private string Format(object p, string format)
        {
            string valor = Convert.ToString(p);
            return string.IsNullOrWhiteSpace(valor) ? string.Empty : string.Format(format, valor);
        }
    }
}
