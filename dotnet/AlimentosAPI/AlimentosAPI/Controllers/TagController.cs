using AlimentosAPI.Domain.Services.Interfaces;
using AlimentosAPI.Models;
using Microsoft.AspNetCore.Mvc;

namespace AlimentosAPI.Controllers
{
    [Route("api/tags")]
    public class TagController : ComumController<Tag>
    {
        public TagController(IComumService<Tag> service) : base(service) { }
    }
}
