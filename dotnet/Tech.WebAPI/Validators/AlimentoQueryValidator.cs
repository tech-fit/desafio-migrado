using FluentValidation;
using Tech.WebAPI.Models;

namespace Tech.WebAPI.Validators
{
    public class AlimentoQueryValidator : AbstractValidator<AlimentoQuery>
    {
        public AlimentoQueryValidator()
        {
            RuleFor(x => x.Alimento)
                .NotEmpty().WithMessage("Parâmetro inválido")
                .MinimumLength(3).WithMessage("Pesquisa deve possuir ao menos 3 caracteres")
                .MaximumLength(15).WithMessage("Limite para pesquisa de 15 caracteres");

        }
    }
}
