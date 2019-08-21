using System.Collections.Generic;

namespace AlimentosAPI.Repository.Interfaces
{
    public interface IComumRepository<T>
    {
        bool Exists(string nome);
        bool Exists(int id);
        T Create(T model);
        List<T> ReadAll(string name = "");
        T Update(T model);
        void Delete(int id);
    }
}
