using Microsoft.AspNetCore.Mvc;
using System.Collections.Generic;
using System.Threading.Tasks;
using Tech.WebAPI.Models;
using Tech.WebAPI.Service;

namespace Tech.WebAPI.Controllers
{
    [Route("api/[controller]")]
    public class AlimentoController : Controller
    {
        private readonly IAlimentoService _AlimentoService;

        public AlimentoController(IAlimentoService service) => _AlimentoService = service;

        [HttpGet]
        public async Task<ActionResult<IEnumerable<AlimentoViewModel>>> GetAsync()
        {
            return await _AlimentoService.GetAsync();
        }

        [HttpGet("{id}")]
        public async Task<ActionResult<AlimentoViewModel>> GetAsync(int id)
        {
            return await _AlimentoService.GetAsync(id);
        }

        [HttpPost]
        public IActionResult Post([FromBody] NovoAlimentoViewModel model)
        {
            if (model == null)
                return BadRequest();

            _AlimentoService.Save(model);
            return Ok(new { Message = "Alimento cadastrado com sucesso." });
        }
    }
}
