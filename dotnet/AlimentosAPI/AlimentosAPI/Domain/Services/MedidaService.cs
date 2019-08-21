using AlimentosAPI.Domain.Services.Interfaces;
using AlimentosAPI.Models;
using AlimentosAPI.Repository.Interfaces;

namespace AlimentosAPI.Domain.Services
{
    public class MedidaService : ComumService<Medida>, IComumService<Medida>
    {
        public MedidaService(IComumRepository<Medida> service) : base(service) { }
    }
}
