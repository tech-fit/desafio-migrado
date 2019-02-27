using Microsoft.VisualStudio.TestTools.UnitTesting;
using Tech.WebAPI.Domain.Exception;
using Tech.WebAPI.Models;

namespace Tech.Teste
{
    [TestClass]
    public class AlimentoEntityTest
    {
        [TestMethod]
        [ExpectedException(typeof(AlimentoNotFoundException))]
        public void ThrowAlimentoException()
        {
            new AlimentoViewModel(null);
        }
    }
}
