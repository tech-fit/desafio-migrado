using System;
using System.Collections.Generic;

namespace AlimentosAPI.Models
{
    public class Alimento : Model
    {
        public int QuantidadeGramas { get; set; }
        public int Calorias { get; set; }
        public double Carboidratos { get; set; }
        public double Proteinas { get; set; }
        public double GordurasTotais { get; set; }
        public double GordurasSaturadas { get; set; }
        public double FibraAlimentar { get; set; }
        public int Sodio { get; set; }
        public IList<AlimentoMedida> Medidas { get; set; }
        public IList<AlimentoTag> Tags { get; set; }

        private const string mensagem = "Incorrect parameter: ";
        public override void Merge(Model model)
        {
            var alimento = (Alimento)model;

            Nome = alimento.Nome;
            DataAlteracao = DateTime.Now;
            QuantidadeGramas = alimento.QuantidadeGramas;
            Calorias = alimento.Calorias;
            Carboidratos = alimento.Carboidratos;
            Proteinas = alimento.Proteinas;
            GordurasTotais = alimento.GordurasTotais;
            GordurasSaturadas = alimento.GordurasSaturadas;
            FibraAlimentar = alimento.FibraAlimentar;
            Sodio = alimento.Sodio;
            Medidas = alimento.Medidas;
            Tags = alimento.Tags;
        }
        public override void Validacao()
        {
            if (QuantidadeGramas < 0)
                throw new ArgumentException($"{mensagem}Quantidade Gramas");

            if (Calorias < 0)
                throw new ArgumentException($"{mensagem}Calorias");

            if (Carboidratos < 0)
                throw new ArgumentException($"{mensagem}Carboidratos");

            if (Proteinas < 0)
                throw new ArgumentException($"{mensagem}Proteinas");

            if (GordurasTotais < 0)
                throw new ArgumentException($"{mensagem}Gorduras Totais");

            if (GordurasSaturadas < 0)
                throw new ArgumentException($"{mensagem}Gorduras Saturadas");

            if (FibraAlimentar < 0)
                throw new ArgumentException($"{mensagem}Fibra Alimentar");

            if (Sodio < 0)
                throw new ArgumentException($"{mensagem}Sódio");
        }
    }
}
