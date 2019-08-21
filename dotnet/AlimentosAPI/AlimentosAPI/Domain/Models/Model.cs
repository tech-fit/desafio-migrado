using AlimentosAPI.Models.Interfaces;
using Newtonsoft.Json;
using System;

namespace AlimentosAPI.Models
{
    public abstract class Model: IIdentity
    {
        public string Nome { get; set; }
        public int Id { get; set; }

        [JsonIgnore]
        public bool IsAtivo { get; set; }

        [JsonIgnore]
        public DateTime DataCriacao { get; set; }

        [JsonIgnore]
        public DateTime DataAlteracao { get; set; }

        public virtual void Validacao() { }
        public virtual void Merge(Model model)
        {
            Nome = model.Nome;
            DataAlteracao = DateTime.Now;
        }
    }
}
