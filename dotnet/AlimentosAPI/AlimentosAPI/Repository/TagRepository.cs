using AlimentosAPI.Infrastructure;
using AlimentosAPI.Models;
using AlimentosAPI.Repository.Interfaces;

namespace AlimentosAPI.Repository
{
    public class TagRepository : ComumRepository<Tag>, IComumRepository<Tag>
    {
        public TagRepository(Context context) : base(context) { }
    }
}
