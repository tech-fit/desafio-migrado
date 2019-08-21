using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace AlimentosAPI.Domain.Services.Interfaces
{
    public interface IComumService<T>
    {
        T Create(T dto, ref string message);
        IList<T> ReadAll(ref string message);
        T Update(T model, ref string message);
        bool Excluir(int id, ref string message);
        IList<T> GetBy(string name, ref string message);
    }
}
