using AlimentosAPI.Infrastructure;
using AlimentosAPI.Models;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;

namespace AlimentosAPI.Repository
{
    public abstract class ComumRepository<T> where T : Model
    {
        #region fields
        protected readonly Context context;
        protected readonly DbSet<T> dbSet;
        #endregion fields

        #region constructors
        public ComumRepository(Context context)
        {
            this.context = context;
            this.dbSet = context.Set<T>();
        }
        #endregion constructors

        #region methods
        public T Create(T model)
        {
            model.IsAtivo = true;
            model.DataCriacao = DateTime.Now;
            model.DataAlteracao = model.DataCriacao;

            context.Add(model);
            context.SaveChanges();

            return model;
        }

        public void Delete(int id)
        {
            var toDelete = dbSet.Where(a => a.IsAtivo && a.Id == id)
                                .ToList().FirstOrDefault();

            toDelete.IsAtivo = false;
            toDelete.DataAlteracao = DateTime.Now;

            context.Update(toDelete);
            context.SaveChanges();

        }

        public bool Exists(string nome)
            => dbSet.Where(a => a.Nome.Equals(nome) && a.IsAtivo).ToList().Count > 0 ? true : false;

        public bool Exists(int id)
            => dbSet.Where(a => a.Id == id && a.IsAtivo).ToList().Count > 0 ? true : false;

        public virtual List<T> ReadAll(string name = "")
            => dbSet.Where(t => string.IsNullOrWhiteSpace(name) ? 
                                t.IsAtivo : 
                                t.IsAtivo && t.Nome.ToLower().Contains(name))
                    .ToList();

        public virtual T Update(T model)
        {
            var toUpdate = dbSet.Where(q => q.Id == model.Id && q.IsAtivo).FirstOrDefault();

            toUpdate.Merge(model);

            context.Update(toUpdate);
            context.SaveChanges();
            
            return toUpdate;
        }
        #endregion methods
    }
}
