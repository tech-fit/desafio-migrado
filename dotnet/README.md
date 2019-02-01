# Desafio em .NET da tech.fit: ASP.NET e Web API

## Necessidades

Neste projeto, temos a necessidade de criar um cadastro de Alimentos e uma Web API (REST FULL) que ofereça os dados das Alimentos que foram cadastrados. As informações servidas pela API serão consumidas por cerca de 400 a 700 devices mobiles no mesmo momento. As áreas de negócio não necessitam que ao atualizar uma informação de um instrumento ela esteja atualizada em realtime para os devices que consomem a API (pode existir alguns minutos para que uma informação nutricional seja atualizada na api).

Um Alimento possui algumas características como: 

* Nome

* Quantidade de gramas

* Calorias

* Carboidratos

* Proteínas

* Gorduras totais

* Gorduras saturadas

* Fibra alimentar

* Sódio

* Medidas

* Tags



Cada alimento pode ter medidas como: Colher de sopa, Escumadeira, Xicara. Ex:

    Arroz
        100 gramas
        Calorias (valor energético)	128 kcal
        Carboidratos	28,1g
        Proteínas	2,5g
        Gorduras totais	0,2g
        Gorduras saturadas	0,05g
        Fibra alimentar	1,6g
        Sódio	1mg

    Medidas
        Escumadeira: 30 gramas
        Xicara: 200 gramas
        Colher de sopa: 25 gramas


    Pão francês
        40 gramas
        Calorias (valor energético)	150 kcal
        Carboidratos	29,3 g
        Proteínas	4 g
        Gorduras totais	1,55 g
        Gorduras saturadas	0,5 g
        Fibra alimentar	1,15 g
        Sódio	324 mg


    Medidas
        Unidade: 40 gramas
        Fatia: 15 gramas


SGBD pode se utilizar o LocalDB.
Layout pode se utilizar o template padrão do ASP.NET MVC ou um de sua preferencia.

# Itens avaliados #

* Acesso a dados
* Validações
* Testes
* SOLID
* Tratamento de erros

## Desafio

Uma grande necessidade seria implementar um endpoint com mecanismo de busca por alimento.