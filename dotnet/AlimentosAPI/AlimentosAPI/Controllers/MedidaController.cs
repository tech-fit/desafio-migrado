using AlimentosAPI.Domain.Services.Interfaces;
using AlimentosAPI.Models;
using Microsoft.AspNetCore.Mvc;

namespace AlimentosAPI.Controllers
{
    [Route("api/medidas")]
    public class MedidaController : ComumController<Medida>
    {
        public MedidaController(IComumService<Medida> service) : base(service) { }
    }
}
