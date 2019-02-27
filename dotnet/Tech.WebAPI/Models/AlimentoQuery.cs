using System;

namespace Tech.WebAPI.Models
{
    [Serializable]
    public class AlimentoQuery
    {
        private string _alimento;

        public string Alimento
        {
            get { return _alimento?.ToLower(); }
            set { _alimento = value; }
        }

    }
}
