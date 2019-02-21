using Microsoft.VisualStudio.TestTools.UnitTesting;
using Tech.Dominio.ValueObjects;

namespace Tech.Teste
{
    [TestClass]
    public class ValueObjectTest
    {
        [TestMethod]
        public void ToStringRetornaFormatoCorreto()
        {
            const string formato = "#gluten";
            var tag = new Tag("GLUTEN");
            Assert.AreEqual(formato, tag.ToString());
        }

        [TestMethod]
        public void ToEqualsIsTrue()
        {
            var tag1 = new Tag("fibra");
            var tag2 = new Tag("fibra");
            Assert.IsTrue(tag1.Equals(tag2));
        }

        [TestMethod]
        public void ToEqualsIsFalse()
        {
            var tag1 = new Tag("fibra");
            var tag2 = new Tag("gordura");
            Assert.IsFalse(tag1.Equals(tag2));
        }
    }
}
