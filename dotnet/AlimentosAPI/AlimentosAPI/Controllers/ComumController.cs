using AlimentosAPI.Domain.Services.Interfaces;
using AlimentosAPI.Models;
using Microsoft.AspNetCore.Mvc;
using System.Collections.Generic;

namespace AlimentosAPI.Controllers
{
    [ApiController]
    public abstract class ComumController<T> : Controller where T : Model
    {
        #region fields
        protected readonly IComumService<T> service;
        protected const string messageBadRequest = "It isn't possible to complete the action";

        #endregion fiels

        #region constructors
        public ComumController(IComumService<T> service)
        {
            this.service = service;
        }

        #endregion constructors

        #region aux methods
        protected ActionResult BuildResult(T model, string message = "")
        {
            if (model == null)
                return BadRequest($"{messageBadRequest}. Error: {message}");

            else if (model.Id == 0)
                return NotFound();

            return Ok(model);
        }

        protected ActionResult BuildResult(IList<T> models, string message = "")
        {
            if (models == null)
                return BadRequest($"{messageBadRequest}. Error: {message}");

            else if (models.Count == 0)
                return NotFound();

            return Ok(models);
        }
        #endregion aux methods

        #region crud
        [HttpPost]
        public ActionResult Create([FromBody] T model)
        {
            var message = string.Empty;
            return BuildResult(service.Create(model, ref message), message);
        }

        [HttpGet]
        public ActionResult Read()
        {
            var message = string.Empty;
            return BuildResult(service.ReadAll(ref message), message);
        }

        [HttpPut]
        public ActionResult Update([FromBody] T model)
        {
            var message = string.Empty;
            return BuildResult(service.Update(model, ref message), message);
        }

        [HttpDelete]
        [Route("{id}")]
        public ActionResult Delete(int id)
        {
            var message = string.Empty;

            if (service.Excluir(id, ref message))
                return NoContent();

            return BadRequest($"{messageBadRequest}. Error: {message}");
        }

        #endregion crud

        #region filter
        [HttpGet]
        [Route("filter/{name}")]
        public ActionResult GetBy(string name)
        {
            var message = string.Empty;
            return BuildResult(service.GetBy(name, ref message), message);
        }

        #endregion filter
    }
}
