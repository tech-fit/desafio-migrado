using AlimentosAPI.Domain.Services.Interfaces;
using AlimentosAPI.Models;
using AlimentosAPI.Repository.Interfaces;

namespace AlimentosAPI.Domain.Services
{
    public class TagService : ComumService<Tag>, IComumService<Tag>
    {
        public TagService(IComumRepository<Tag> repository) : base(repository) { }
    }
}
