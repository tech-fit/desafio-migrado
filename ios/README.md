# Criar um aplicativo de consulta na api do [Dribbble](https://dribbble.com) #

Criar um aplicativo para consultar a [Dribbble API](http://developer.dribbble.com/v1/) e trazer os shots + populares . Basear-se no mockup fornecido:

![Screen Shot 2014-10-09 at 3.42.06 PM.png](https://bitbucket.org/repo/bApLBb/images/3039998141-Screen%20Shot%202014-10-09%20at%203.42.06%20PM.png)

Nos envie uma solução mesmo que você não consiga fazer tudo. O teste serve pra conhecermos a sua forma de pensar, resolver problemas e seu estilo de código.

# Deve conter #

* Arquivo .gitignore
* Usar Storyboard e Autolayout
* Gestão de dependências no projeto. Ex: Cocoapods
* Framework para Comunicação com API. Ex:  AFNetwork
* Mapeamento json -> Objeto . Ex: [Mantle](https://github.com/Mantle/Mantle#mtlmodel)
* Lista de shots API (http://api.dribbble.com/shots/popular?page=1)
* Paginação automática (scroll infinito) na tela de lista de shots
* Paginação deve detectar quando chega a última página e parar de solicitar mais
* Pull to refresh
* Tela de detalhe de um shot ao clicar em um item da lista de shots
* Tela de detalhe de um shot deve conter nome do autor, foto e descrição do shot
* Cache de Imagens / Download Assincrono. Ex SDWebImage

# Extras #

* Testes unitários no projeto. Ex: XCTests
* Testes funcionais. Ex: KIF
* App Universal , Ipad | Iphone | Landscape | Portrait (Size Classes)
* Compartilhar shots no facebook e twitter

# Submissão #

Para iniciar o desafio, faça um fork deste repositório, crie uma branch com o seu nome e depois envie pra gente o pull request.
Se você apenas clonar o repositório não vai conseguir fazer push pra gente e depois vai ser mais complicado fazer o pull request.