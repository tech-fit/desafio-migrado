using FluentValidation;
using Tech.WebAPI.Domain.ValueObjects;
using Tech.WebAPI.Models;

namespace Tech.WebAPI.Validators
{
    public class NovoAlimentoValidator : AbstractValidator<NovoAlimentoViewModel>
    {
        public NovoAlimentoValidator()
        {
            RuleFor(x => x.Nome)
                .NotEmpty().WithMessage("Nome é obrigatório")
                .MaximumLength(100).WithMessage("Limite de 100 caracteres");

            RuleFor(x => x.Peso).Must(ValidarDecimal).WithMessage(MsgDecimalInvalido);
            RuleFor(x => x.Caloria).Must(ValidarDecimal).WithMessage(MsgDecimalInvalido);
            RuleFor(x => x.Carboidrato).Must(ValidarDecimal).WithMessage(MsgDecimalInvalido);
            RuleFor(x => x.Proteina).Must(ValidarDecimal).WithMessage(MsgDecimalInvalido);
            RuleFor(x => x.GorduraSaturada).Must(ValidarDecimal).WithMessage(MsgDecimalInvalido);
            RuleFor(x => x.GorduraTotal).Must(ValidarDecimal).WithMessage(MsgDecimalInvalido);
            RuleFor(x => x.FibraAlimentar).Must(ValidarDecimal).WithMessage(MsgDecimalInvalido);
            RuleFor(x => x.Sodio).Must(ValidarDecimal).WithMessage(MsgDecimalInvalido);
            When(x => !string.IsNullOrWhiteSpace(x.Tag), () =>
               {
                   RuleFor(x => x.Tag)
                   .Must(EstarSeparadaPorVirgula)
                   .WithMessage("As tags devem estar separadas por virgula");
               });
        }
        private bool EstarSeparadaPorVirgula(string tags)
        {
            string[] myTgags = tags.Trim().Split(",");
            return myTgags.Length >= 1;
        }

        private bool ValidarDecimal(decimal? d)
        {
            if (d.HasValue)
                return d.Value >= 0;

            return true;
        }

        private string MsgDecimalInvalido = "Valor deve ser maior ou igual a zero.";
    }
}
