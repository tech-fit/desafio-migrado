using AlimentosAPI.Domain.Services.Interfaces;
using AlimentosAPI.Models;
using AlimentosAPI.Repository.Interfaces;

namespace AlimentosAPI.Domain.Services
{
    public class AlimentoService : ComumService<Alimento>, IComumService<Alimento>
    {
        public AlimentoService(IComumRepository<Alimento> repository) : base(repository) { }
    }
}
