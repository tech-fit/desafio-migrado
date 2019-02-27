using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using Tech.WebAPI.Domain.Exception;
using Tech.WebAPI.Models;
using Tech.WebAPI.Service;

namespace Tech.WebAPI.Controllers
{
    [Route("api/alimentos")]
    public class AlimentoController : Controller
    {
        private readonly IAlimentoService _AlimentoService;

        public AlimentoController(IAlimentoService service) => _AlimentoService = service;

        [HttpGet,Route("all")]
        public async Task<ActionResult<IEnumerable<AlimentoViewModel>>> GetAsync()
        {
            return Ok(await _AlimentoService.GetAsync());
        }

        [HttpGet("{id}")]
        public async Task<ActionResult<AlimentoViewModel>> GetAsync(int id)
        {
            try
            {
                return Ok(await _AlimentoService.GetAsync(id));
            }
            catch (AlimentoNotFoundException ex)
            {
                return BadRequest(ex.Message);
            }
        }

        [HttpGet]
        public async Task<ActionResult<AlimentoViewModel>> GetAsync([FromBody] AlimentoQuery query)
        {
            if (!ModelState.IsValid)
                return BadRequest(ModelState);

            return Ok(await _AlimentoService.GetAsync(query));
        }

        [HttpPost]
        public IActionResult Post([FromBody] NovoAlimentoViewModel model)
        {
            if (!ModelState.IsValid)
                return BadRequest(ModelState);

            try
            {
                _AlimentoService.Save(model);
                return Ok(new { Message = "Alimento cadastrado com sucesso." });
            }
            catch (AlimentoServiceException ex)
            {
                return BadRequest(ex.Message);
            }
        }
    }
}
