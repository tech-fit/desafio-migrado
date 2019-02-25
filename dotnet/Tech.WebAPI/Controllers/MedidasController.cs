using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Tech.WebAPI.Domain;
using Tech.WebAPI.ModelViews;
using Tech.WebAPI.Persistence;

namespace Tech.WebAPI.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class MedidasController : ControllerBase
    {
        private readonly TechContext _db;

        public MedidasController(TechContext context)
        {
            _db = context;
        }

        [HttpGet]
        public async Task<ActionResult<IEnumerable<Medida>>> GetAsync()
        {
            var entities = await _db.Medidas.ToListAsync();

            if (!entities.Any())
                return NotFound();

            return entities;
        }

        [HttpGet("{id}")]
        public async Task<ActionResult<MedidaViewModel>> GetAsync(int id)
        {
            var entity = await _db.Medidas.FindAsync(id);

            if (entity == null)
                return NotFound();

            return MedidaViewModel.GetFrom(entity);
        }

        [HttpPost]
        public async Task<IActionResult> Post([FromBody] Medida medida)
        {
            _db.Medidas.Add(medida);
            await _db.SaveChangesAsync();

            return NoContent();
        }
    }
}
