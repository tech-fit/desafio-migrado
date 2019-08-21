using AlimentosAPI.Domain.Services.Interfaces;
using AlimentosAPI.Models;
using Microsoft.AspNetCore.Mvc;

namespace AlimentosAPI.Controllers
{
    [Route("api/alimentos")]
    public class AlimentoController : ComumController<Alimento>
    {
        public AlimentoController(IComumService<Alimento> service) : base(service) { }
    }
}
