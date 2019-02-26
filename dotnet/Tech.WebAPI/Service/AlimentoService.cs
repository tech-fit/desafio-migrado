using Microsoft.EntityFrameworkCore;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Tech.WebAPI.Domain;
using Tech.WebAPI.Domain.Exception;
using Tech.WebAPI.Models;
using Tech.WebAPI.Persistence;

namespace Tech.WebAPI.Service
{
    public class AlimentoService : IAlimentoService
    {
        private readonly TechContext _db;

        public AlimentoService(TechContext context) => _db = context;

        public async Task<AlimentoViewModel> GetAsync(int id)
        {
            var entity = await _db.Alimentos.FindAsync(id);

            if (entity == null)
                throw new AlimentoNotFoundException();

            return new AlimentoViewModel(entity);
        }

        public async Task<List<AlimentoViewModel>> GetAsync()
        {
            var entities = await _db.Alimentos.ToListAsync();

            if (entities == null)
                throw new AlimentoNotFoundException();

            return entities.Select(x => new AlimentoViewModel(x)).ToList();
        }

        public void Save(NovoAlimentoViewModel alimento)
        {
            var entity = new Alimento
            {
                Nome = alimento.Nome,
                Peso = alimento.Peso,
                Caloria = alimento.Caloria,
                Proteina = alimento.Proteina,
                Carboidrato = alimento.Carboidrato,
                GorduraTotal = alimento.GorduraTotal,
                GorduraSaturada = alimento.GorduraSaturada,
                FibraAlimentar = alimento.FibraAlimentar,
                Sodio = alimento.Sodio
            };

            _db.Alimentos.Add(entity);
            _db.SaveChanges();
        }
    }
}
