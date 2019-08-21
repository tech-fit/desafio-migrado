using Newtonsoft.Json;
using System;
using System.Collections.Generic;

namespace AlimentosAPI.Models
{
    public class Medida : Model
    {
        [JsonIgnore]
        public IList<AlimentoMedida> Alimentos { get; set; }

        public override void Merge(Model model)
        {
            Nome = model.Nome;
            DataAlteracao = DateTime.Now;
        }
    }
}
