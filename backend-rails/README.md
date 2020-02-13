# Desafio em RAILS da tech.fit

## Necessidades

Neste projeto, temos a necessidade de criar uma Web API (REST FULL) para criação de Usuários, Alimentos, Medidas e Refeições.

**Deve existir o CRUD de cada uma dessas entidades**

Também deverá existir alguma interface que se comunique com a Web API, ela pode ser construída utilizando HTML/CSS/JS ou com
algum framework web de sua escolha.

Um Usuário deve ser capaz de se autenticar utilizando Email e Senha, além disso deve ter algumas informações:

* Nome

* Data de nascimento (o usuário deve ter no mínimo 18 anos)

* Idade (calculada a partir da data de nascimento)

* Altura (cm) (deve ser maior que 100 e menor que 250)

* Peso (kg) (deve ser maior que 40 e menor que 200)


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

* Usuário

* Medidas


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

Uma Refeição possui algumas características como:

* Tipo da refeição: Café da manhã, Lanche da manhã, Almoço, Lanche da tarde, Jantar

* Comentário

* Alimentos (cada refeição deve ter no mínimo 1 alimento)


# Requisitos #

* Deve existir uma tela que mostra os últimos alimentos adicionados, ela deve possuir algum mecânismo de paginação.

* Essa tela deve estar disponível para Usuários autenticados e não autenticados. Para ver os dados da refeição é necessário estar autenticado

* Deve existir uma busca dos Alimentos de um Usuário pelo Nome do Alimento.

* Em pelo menos um ponto da Web API deverá ser utilizado cache que melhore seu desempenho.

* Nas informações de cada Refeição deverá estar listado cada Alimento com seus valores nutricionais e a soma desses valores.

* Qualquer alteração em informação de um Alimento ou de uma Medida NÃO deverá alterar informações de Refeições já adicionadas.

# Itens avaliados #

* Qualidade do código
* Qualidade na interface
* Acesso a dados
* Validações
* Tratamento de erros