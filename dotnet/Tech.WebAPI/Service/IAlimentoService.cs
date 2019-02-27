using System.Collections.Generic;
using System.Threading.Tasks;
using Tech.WebAPI.Models;

namespace Tech.WebAPI.Service
{
    public interface IAlimentoService
    {
        Task<AlimentoViewModel> GetAsync(int id);
        Task<List<AlimentoViewModel>> GetAsync();
        Task<List<AlimentoViewModel>> GetAsync(AlimentoQuery query);
        void Save(NovoAlimentoViewModel alimento);
    }
}
