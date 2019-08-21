using AlimentosAPI.Infrastructure;
using AlimentosAPI.Models;
using AlimentosAPI.Repository.Interfaces;

namespace AlimentosAPI.Repository
{
    public class MedidaRepository : ComumRepository<Medida>, IComumRepository<Medida>
    {
        public MedidaRepository(Context context) : base(context) { }
    }
}
