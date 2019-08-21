using AlimentosAPI.Models.Interfaces;
using Newtonsoft.Json;

namespace AlimentosAPI.Models
{
    public class AlimentoMedida : IIdentity
    {
        public double Quantidade { get; set; }
        public Medida Medida { get; set; }
        public int MedidaId { get; set; }

        [JsonIgnore]
        public int Id { get; set; }

        [JsonIgnore]
        public int AlimentoId { get; set; }

        [JsonIgnore]
        public Alimento Alimento { get; set; }
    }
}
