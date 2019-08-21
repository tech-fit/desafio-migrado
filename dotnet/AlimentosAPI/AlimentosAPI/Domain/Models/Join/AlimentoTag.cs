using AlimentosAPI.Models.Interfaces;
using Newtonsoft.Json;

namespace AlimentosAPI.Models
{
    public class AlimentoTag : IIdentity
    {
        
        public int TagId { get; set; }
        public Tag Tag { get; set; }

        [JsonIgnore]
        public int Id { get; set; }

        [JsonIgnore]
        public int AlimentoId { get; set; }

        [JsonIgnore]
        public Alimento Alimento { get; set; }
    }
}
