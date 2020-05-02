# Desafio Estágio Mobile da tech.fit: Android App.

Uma das áreas de um dos nossos apps, o Tecnonutri, é uma área social estilo Instagram onde as pessoas postam refeições fitness.

Para esse teste, você deverá clonar esse repositório com um projeto Android já existente, e criar 2 telas de visualização dos endpoints da nossa rede social.

Caso você não conheça como utilizar o github https://help.github.com/articles/fork-a-repo/

Se mesmo assim, nada der certo, envie seu teste para tecnologia@tech.fit


Atente para:

 * Após a conclusão, o teste deve ser submetido em forma de pull-request.

 * O projeto apresenta a Tela 1 (Feed) já implementada, você deverá realizar a implementação das telas 2 (Detalhes da Postagem) e 3 (Detalhes do Perfil).

 * Atente para guidelines de design, não esperamos que você seja um super designer, mas manter uma boa harmonia visual irá contar pontos.

 * E não se esqueça: nós nos impressionamos muito mais com um app acabadinho digno de subir para a google play do que com aquela arquitetura cabulosa copiada da internet (mas a gente lê código também, então capricha).

 * Temos alguns itens opcionais que valem pontos extras caso sejam implementados! ;)
 


## Requisitos Técnicos:

 * O teste deve seguir a implementação em Kotlin (https://developer.android.com/kotlin/resources).

 * Para carregamento de imagens deverá usar Picasso (https://square.github.io/picasso/).

 * Para requisições de endpoints, deverá usar Retrofit (https://square.github.io/retrofit/) e/ou OkHttp (https://square.github.io/okhttp/).

 * (ATENÇÃO) Um aplicativo pode ter muitos estados, atente-se para: Carregamento, Listas Vazias, Erros, etc.

 * (Sugestão) Para armazenar dados locais, poderá usar o próprio shared preferences do Android (https://developer.android.com/training/data-storage/shared-preferences).
 
 * (Opcional) Poder abrir diretamente um post usando as uris testetechfit://feed/{feedHash} e um perfil usando as uris testechfit://profile/{id} (https://developer.android.com/training/app-links/deep-linking).


## Tela 1: Feed (Já está implementada)

<img width="200" alt="Tela 1: Feed" src="https://i.imgur.com/it0TxDy.png">

Endpoint: GET http://api.tecnonutri.com.br/api/v4/feed


### Comportamento (OK): 
 * Nesse endpoint você receberá um json com uma série de posts (key: "items") com cada um dos cards que irá mostrar. Ao tocar no perfil do usuário, abre-se a tela do perfil (Tela 3), ou ao tocar no resto do card, abre-se a tela dos detalhes do post (Tela 2).


### Paginação (OK): 
 * Essa tela utiliza o scroll infinito, cada request retorna dois campos: "p" e "t". O campo "p" é o número da página retornada e o campo "t" é um timestamp utilizado para fazer o scroll infinito. Ao chegar próximo do fim do scroll, deverá fazer um novo request para o endpoint: http://api.tecnonutri.com.br/api/v4/feed?p={p}&t={t} com p e t sendo substituídos pelos valores do request da página anterior. Os itens retornados nesse novo request devem então ser adicionados ao fim da página.


### Reload (OK): 
 * É desejável que a tela tenha um pull-to-refresh que irá apagar todos os itens e refazer o request inicial com página 0 e sem timestamp atualizando novamente todo o feed.


### Curtir (Opcional): 
 * Cada card deverá ter um botão em forma de coração para curtir (apresentando um contorno de coração) e descurtir (apresentando como um coração cheio) a publicação. As publicações curtidas deverão ser persistidas localmente sem necessidade de chamar nenhum endpoint, mas deverá ser consistente todas as vezes que abrir o app e ao trocar de telas (por exemplo, esperamos ver uma curtida ocorrendo na tela de detalhes e quando o usuário retorna para a tela anterior a publicação será exibida como curtida).


### Mapeamento dos dados com o json:


 Cada item na key "items" é um card.


 Foto do Autor: "profile"."image". Pode vir null.

 Nome do Autor: "profile"."name". Pode vir null ou vazio.

 Objetivo do Autor: "profile"."general_goal". Pode vir null ou vazio.


 Foto da refeição: "image"

 Refeição: "meal" sendo: 0=Café da Manhã, 1=Lanche da Manhã, 2=Almoço, 3=Lanche da Tarde, 4=Jantar, 5=Ceia, 6=Pré-Treino, 7=Pós-Treino.

 Data: "date"

 Kcal: "energy", Pode ser null


 Os demais dados retornados pelo endpoint podem ser ignorados.


## Tela 2: Detalhes da Postagem

<img width="200" alt="Tela 2: Detalhes da Postagem" src="https://i.imgur.com/eFvbBnF.png">

Endpoint: GET http://api.tecnonutri.com.br/api/v4/feed/{feedHash} -> feedHash é uma propriedade que veio junto na tela anterior.


### Comportamento: 
 * Nesse endpoint você receberá um json com um único post com os detalhes da refeição. Ao tocar no perfil do usuário, abre-se a tela do perfil (Tela 3), é possível voltar para a tela de feed.


### Paginação: 
 * Não há paginação nessa tela.


### Reload: 
 * É desejável que a tela tenha um pull-to-refresh que irá apagar todos os itens e refazer o request atualizando novamente toda a tela.


### Curtir (Opcional): 
 * Cada card deverá ter um botão em forma de coração para curtir (apresentando um contorno de coração) e descurtir (apresentando como um coração cheio) a publicação. As publicações curtidas deverão ser persistidas localmente sem necessidade de chamar nenhum endpoint, mas deverá ser consistente todas as vezes que abrir o app e ao trocar de telas (por exemplo, esperamos ver uma curtida ocorrendo na tela de detalhes e quando o usuário retorna para a tela anterior a publicação será exibida como curtida).


### Mapeamento dos dados com o json:


 Os dados estão dentro da key "item".


 Foto do Autor: "profile"."image". Pode vir null.

 Nome do Autor: "profile"."name". Pode vir null ou vazio.

 Objetivo do Autor: "profile"."general_goal". Pode vir null ou vazio.


 Foto da refeição: "image"

 Refeição: "meal" sendo: 0=Café da Manhã, 1=Lanche da Manhã, 2=Almoço, 3=Lanche da Tarde, 4=Jantar, 5=Ceia, 6=Pré-Treino, 7=Pós-Treino.

 Data: "date"

 Kcal: "energy", Pode ser null


 Cada row de alimento está dentro de "foods", alguns posts podem não ter nenhum alimento associado.

   

   Nome do alimento: "description"

   Quantidade da medida: "amount"

   Unidade da medida: "measure"

   Peso em gramas: "weight"

   Calorias: "energy"

   Carbohidratos: "carbohydrate"

   Gordura: "fat"

   Proteinas: "protein"


 Total da refeição está na raíz do "item" podendo ser null.


   Calorias: "energy"

   Carbohidratos: "carbohydrate"

   Gordura: "fat"

   Proteinas: "protein"


 Os demais dados retornados pelo endpoint podem ser ignorados.


## Tela 3: Detalhes do Perfil

<img width="200" alt="Tela 3: Detalhes do Perfil" src="https://i.imgur.com/X57djvJ.png">

Endpoint: GET http://api.tecnonutri.com.br/api/v4/profile/{id}


### Comportamento: 
 * Nesse endpoint você receberá um json com os dados do perfil do usuário e uma série de posts (key: "items") com cada um dos cards que irá mostrar. Ao tocar em algum card, abre-se a tela dos detalhes do post (Tela 2).


### Paginação: 
 * Essa tela utiliza o scroll infinito, cada request retorna dois campos: "p" e "t". O campo "p" é o número da página retornada e o campo "t" é um timestamp utilizado para fazer o scroll infinito. Ao chegar próximo do fim do scroll, deverá fazer um novo request para o endpoint: http://api.tecnonutri.com.br/api/v4/profile/{id}?p={p}&t={t} com p e t sendo substituídos pelos valores do request da página anterior. Os itens retornados nesse novo request devem então ser adicionados ao fim da página.


### Reload: 
 * É desejável que a tela tenha um pull-to-refresh que irá apagar todos os itens e refazer o request inicial com página 0 e sem timestamp atualizando novamente todo o feed.


Mapeamento dos dados com o json:


 Foto do Autor: "profile"."image". Pode vir null.

 Nome do Autor: "profile"."name". Pode vir null ou vazio.

 Objetivo do Autor: "profile"."general_goal". Pode vir null ou vazio.


 Cada item na key "items" é um card.


 Foto da refeição: "image"


 Os demais dados retornados pelo endpoint podem ser ignorados.
