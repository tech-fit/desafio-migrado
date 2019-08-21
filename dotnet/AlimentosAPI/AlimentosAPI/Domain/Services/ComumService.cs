using AlimentosAPI.Models;
using AlimentosAPI.Repository.Interfaces;
using System;
using System.Collections.Generic;

namespace AlimentosAPI.Domain.Services
{
    public abstract class ComumService<T> where T : Model
    {
        #region fields
        protected readonly IComumRepository<T> repository;
        protected const string msgAlreadyExists = "Already exists!";
        protected const string msgNotExists = "This element doesn't exist!";
        #endregion fields

        #region constructors
        public ComumService(IComumRepository<T> repository)
        {
            this.repository = repository;
        }

        #endregion constructors

        #region methods
        public T Create(T model, ref string message)
        {
            try
            {
                model.Validacao();

                if (repository.Exists(model.Nome))
                    throw new ArgumentException(msgAlreadyExists);

                return repository.Create(model);
            }
            catch (Exception ex)
            {
                message = ex.Message;
                return null;
            }
        }

        public IList<T> ReadAll(ref string message)
        {
            try
            {
                return repository.ReadAll();
            }
            catch (Exception ex)
            {
                message = ex.Message;
                return null;
            }
        }

        public T Update(T model, ref string message)
        {
            try
            {
                model.Validacao();

                if (!repository.Exists(model.Id))
                    throw new ArgumentException(msgNotExists);

                if (repository.Exists(model.Nome))
                    throw new ArgumentException(msgAlreadyExists);

                return repository.Update(model);
            }
            catch (Exception ex)
            {
                message = ex.Message;
                return null;
            }
        }

        public bool Excluir(int id, ref string message)
        {
            try
            {
                if (!repository.Exists(id))
                    throw new ArgumentException(msgNotExists);

                repository.Delete(id);

                return true;
            }
            catch (Exception ex)
            {
                message = ex.Message;
                return false;
            }
        }

        public IList<T> GetBy(string name, ref string message)
        {
            try
            {
                return repository.ReadAll(name.ToLower());
            }
            catch (Exception ex)
            {
                message = ex.Message;
                return null;
            }
        }
        #endregion methods
    }
}
