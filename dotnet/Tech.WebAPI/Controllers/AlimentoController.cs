using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using System.Collections.Generic;
using System.Threading.Tasks;
using Tech.WebAPI.Domain;
using Tech.WebAPI.ModelViews;
using Tech.WebAPI.Persistence;

namespace Tech.WebAPI.Controllers
{
    [Route("api/[controller]")]
    public class AlimentoController : Controller
    {
        private readonly TechContext _db;

        public AlimentoController(TechContext context) => _db = context;

        [HttpGet]
        public async Task<ActionResult<IEnumerable<Alimento>>> GetAsync()
        {
            return await _db.Alimentos.ToListAsync();
        }

        [HttpGet("{id}")]
        public async Task<ActionResult<Alimento>> GetAsync(int id)
        {
            return await _db.Alimentos.FindAsync(id);
        }

        [HttpPost]
        public IActionResult Post([FromBody] AlimentoViewModel model)
        {
            if (model == null)
                return BadRequest();

            return Ok(new { Message = "Alimento cadastrado com sucesso" });
        }
    }
}
