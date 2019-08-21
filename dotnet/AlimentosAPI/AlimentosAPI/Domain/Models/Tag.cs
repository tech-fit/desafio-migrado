using Newtonsoft.Json;
using System.Collections.Generic;

namespace AlimentosAPI.Models
{
    public class Tag : Model
    {
        [JsonIgnore]
        public IList<AlimentoTag> Alimentos { get; set; }
    }
}
