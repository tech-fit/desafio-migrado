﻿namespace Tech.WebAPI.Domain.ValueObjects
{
    public class Tag
    {
        private string _valor;

        public Tag(string valor)
        {
            _valor = SanitizeTag(valor);
        }

        public static implicit operator string(Tag tag) => tag._valor;

        public static explicit operator Tag(string valor) => new Tag(valor);

        public override string ToString()
        {
            return $"#{_valor.ToLower()} ";
        }

        public override bool Equals(object obj)
        {
            if (obj == null || obj.GetType() != GetType())
                return false;

            Tag tag = (Tag)obj;

            return _valor == tag._valor;
        }

        public override int GetHashCode()
        {
            return base.GetHashCode();
        }

        private string SanitizeTag(string v)
        {
            if (string.IsNullOrWhiteSpace(v))
                return string.Empty;

            return v.Trim().ToLower();
        }
    }
}