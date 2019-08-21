using AlimentosAPI.Infrastructure;
using AlimentosAPI.Models;
using AlimentosAPI.Repository.Interfaces;
using Microsoft.EntityFrameworkCore;
using System.Collections.Generic;
using System.Linq;

namespace AlimentosAPI.Repository
{
    public class AlimentoRepository : ComumRepository<Alimento>, IComumRepository<Alimento>
    {
        #region constructor
        public AlimentoRepository(Context context) : base(context)
        {
        }
        #endregion constructor

        #region methods
        public override List<Alimento> ReadAll(string name = "")
        {
            var alimentoMedida = context.AlimentosMedidas
                                        .Include(m => m.Alimento)
                                        .Include(m => m.Medida)
                                        .Where(a => string.IsNullOrWhiteSpace(name) ? 
                                                    a.Alimento.IsAtivo : 
                                                    a.Alimento.IsAtivo && a.Alimento.Nome.ToLower().Contains(name)
                                        )
                                        .GroupBy(a => a.AlimentoId)
                                        .Select(a => a.First())
                                        .ToList();

            var alimentoTags = context.AlimentosTags
                                      .Include(t => t.Alimento)
                                      .Include(t => t.Tag)
                                      .Where(a => a.Alimento.IsAtivo)
                                      .GroupBy(a => a.AlimentoId)
                                      .Select(a => a.First())
                                      .ToList();


            var resultado = from medida in alimentoMedida
                            join tags in alimentoTags
                            on medida.AlimentoId equals tags.AlimentoId into set
                            from valueDefault in set.DefaultIfEmpty()
                            select new { medida.Alimento }.Alimento;

            return resultado.ToList();
        }

        public override Alimento Update(Alimento model)
        {
            var toUpdate = dbSet.Include(a => a.Medidas)
                                 .Include(a => a.Tags)
                                 .Where(a => a.IsAtivo && a.Id == model.Id)
                                 .ToList().FirstOrDefault();

            toUpdate.Merge(model);

            context.Update(toUpdate);
            context.SaveChanges();

            return model;
        }
        #endregion methods
    }
}
