using AlimentosAPI.Domain.Services;
using AlimentosAPI.Domain.Services.Interfaces;
using AlimentosAPI.Models;
using AlimentosAPI.Repository.Interfaces;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Moq;
using System;
using System.Collections.Generic;

namespace AlimentosAPITest.Services
{
    [TestClass]
    public class ComumServiceTest
    {
        [TestMethod]
        public void ShoudCreateModel()
        {
            var message = string.Empty;

            var alimentoRepo = new Mock<IComumRepository<Alimento>>();

            alimentoRepo.Setup(repo => repo.Create(It.IsAny<Alimento>())).Returns(GetAlimento());
            alimentoRepo.Setup(repo => repo.Exists(It.IsAny<string>())).Returns(false);

            IComumService<Alimento> service = new AlimentoService(alimentoRepo.Object);

            var resultado = service.Create(GetAlimento(), ref message);

            Assert.AreEqual(GetAlimento().Nome, resultado.Nome);
            Assert.AreEqual(string.Empty, message);
        }

        [TestMethod]
        public void TryCreateModelButAlreadyExists()
        {
            var message = string.Empty;

            var alimentoRepo = new Mock<IComumRepository<Alimento>>();

            alimentoRepo.Setup(repo => repo.Create(It.IsAny<Alimento>())).Returns(GetAlimento());
            alimentoRepo.Setup(repo => repo.Exists(It.IsAny<string>())).Returns(true);

            IComumService<Alimento> service = new AlimentoService(alimentoRepo.Object);

            var resultado = service.Create(GetAlimento(), ref message);

            Assert.IsNull(resultado);
            Assert.AreNotEqual(string.Empty, message);
        }

        [TestMethod]
        public void TryCreateModelButException()
        {
            var message = string.Empty;

            var alimentoRepo = new Mock<IComumRepository<Alimento>>();

            alimentoRepo.Setup(repo => repo.Create(It.IsAny<Alimento>())).Throws(new Exception("ex"));
            alimentoRepo.Setup(repo => repo.Exists(It.IsAny<string>())).Returns(false);

            IComumService<Alimento> service = new AlimentoService(alimentoRepo.Object);

            var resultado = service.Create(GetAlimento(), ref message);

            Assert.IsNull(resultado);
            Assert.AreEqual("ex", message);
        }

        [TestMethod]
        public void ShouldReadAllModels()
        {
            var message = string.Empty;

            var alimentoRepo = new Mock<IComumRepository<Alimento>>();

            alimentoRepo.Setup(repo => repo.ReadAll(It.IsAny<string>())).Returns(GetAlimentos());

            IComumService<Alimento> service = new AlimentoService(alimentoRepo.Object);

            var resultado = service.ReadAll(ref message);

            Assert.AreEqual(2, resultado.Count);
            Assert.AreEqual(string.Empty, message);
        }

        [TestMethod]
        public void TryReadAllModelsButException()
        {
            var message = string.Empty;

            var alimentoRepo = new Mock<IComumRepository<Alimento>>();

            alimentoRepo.Setup(repo => repo.ReadAll(It.IsAny<string>())).Throws(new Exception("ex"));

            IComumService<Alimento> service = new AlimentoService(alimentoRepo.Object);

            var resultado = service.ReadAll(ref message);

            Assert.IsNull(resultado);
            Assert.AreEqual("ex", message);
        }

        #region aux methods
        private Alimento GetAlimento()
        {
            return new Alimento()
            {
                Id = 1,
                Nome = "Arroz",
                IsAtivo = true,
                DataCriacao = DateTime.Now,
                DataAlteracao = DateTime.Now
            };
        }

        private List<Alimento> GetAlimentos()
        {
            return new List<Alimento>()
            {
                GetAlimento(),
                GetAlimento()
            };
        }

        #endregion
    }
}
