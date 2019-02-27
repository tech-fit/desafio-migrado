namespace Tech.WebAPI.Domain.Exception
{
    public class AlimentoServiceException : System.Exception
    {
        public AlimentoServiceException()
            : base("Falha ao executar solicitação")
        { }
    }
}
