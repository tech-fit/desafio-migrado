# Teste desenvolvedor mobile iOS

Uma das áreas de um dos nossos apps, o Tecnonutri, é uma área social estilo Instagram onde as pessoas postam refeições fitness. 

Para esse teste, você deverá criar um app iOS nativo, com 2 telas de visualização dos endpoints.

Para efeitos de simulação de dados, as informações virão da seguinte API:
  * https://thecatapi.com/
  * https://documenter.getpostman.com/view/4016432/RWToRJCq

Exemplo de endpoints
  * https://api.thecatapi.com/api/v1/images/?limit=10
  * https://api.thecatapi.com/v1/images/BkIEhN3pG

Para acessar é necessário um token da API, que pode ser conseguido logando no site com seu email.

O aplicativo deverá ter duas interfaces, uma lista com as imagens e um tela de detalhe de uma imagem.

Uma feature que deve ser desenvolvida é o CURTIR de uma das imagens
(com um armazenamento local das fotos que foram curtidas)

A paleta de cores que deve ser utilizada é a seguinte:
* #1c86ee (fundo e topo)
* #ffdd00 (botões)

Existem alguns clientes que passam noite vendo a foto dos gatos, para eles seria necessário a possibilidade de trocar o tema do aplicativo em uma configuração (como se fosse um modo noturno).

* #113266 (fundo e topo)
* #ffffff (botões)


Atente para:

* O código deverá ser repassado para a gente em um repositório do GitHub.

* Atente para guidelines de design, não esperamos que você seja um super designer, mas se atentar aos guides da HIG e manter uma boa harmonia visual considerando que o app deverá ser um produto devidamente lapidado irá contar pontos.

* Atente para os diversos estados que um app que consome uma api pode ter: Listas vazias, Loading durante o request, Retornos de erros.

* E não se esqueça: nós nos impressionamos muito mais com um app acabadinho digno de subir para a app store do que com aquela arquitetura cabulosa copiada da internet (mas a gente lê código também, então capricha).

## Diferencial

Ficar fazendo CRUD é fácil :) mas hoje em dia podemos fazer muito mais né.

Esse desafio é você fazer um filtro de busca no feed por cor do gato. Mas como assim a cor? Não temos esse dado na API! Porém temos a foto! E diversas API’s de Computer Vision API que certamente te ajudará.
