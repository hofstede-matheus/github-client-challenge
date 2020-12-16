## Github Client Challenge

### Como rodar o projeto:
No Android Studio:
- Clone o projeto com ```git clone https://github.com/hofstede-matheus/github-client-challenge.git```
- Abra o projeto com Android Studio e instale 

Direto do celular:
- Habilite a instalação de aplicativos desconhecidos no seu Android
- [Baixe o .apk](https://hofs.dev/github-client-challenge.apk) e instale manualmente

[Kambam do Projeto](https://github.com/hofstede-matheus/github-client-challenge/projects/1)

### Ferramentas escolhidas e porquês:
- Kotlin : Não faz mais sentido começar um projeto com Java, além disso, Kotlin é minha linguagem de progrmação favorita, tanto para desenvolvimento mobile nativo como para backend. A sintaxe é sucinta e idiomática.
- Arquitetura MVVM: A arquitetura em si é minimalista e cumpre o papel de seperação de camadas e facilidade para testes unitários. É a minha arquitetura padrão para qualquer projeto que começo do zero. O Android Architecture Components foi um grande acerto do time Android em relação a padronização da arquitetura.
- Repository Design Pattern: Para tornar a ViewModel mais testável, as dependências (fontes de dados) são injetadas externamente, podendo ser um mock ou uma camada de comunicação com a API.
- Koin: Koin é a cara de Kotlin, logo é muito produtivo de trabalhar. Apesar de eu ser um programador que me sinto mais seguro que as coisas quebrem em tempo de compilação, Koin é uma das primeiras alternativas quando penso em DI. É minimalista e integrado com o Kotlin.
- Kotlin Coroutines: É uma maneira padrão de trabalhar com assicronismo com Kotlin. É eficiente e fácil de usar.
- Testes com JUnit4 + MockK e Espresso: É simples e é o padrão de testes no ecossistema Android. Utilizei MockK pela afinidade com Kotlin.
- Navigation Component, ViewBinding, Androidx, Single-Activity App, Lifecycle Components: É o de mais modeno no desenvolviemnto Android, é fácil de utilizar e dá mais escalabilidade ao código.

### Cobertura de Testes:
#### Testes Unitários:

PublicRepositoriesViewModelTest
- `when getPublicRepositoriesList() succeeds, then publicRepositories should update`
- `when getPublicRepositoriesList() fails, then error should update`
- `when getPublicRepositoriesList() is fetching, then _isFetchingData should be true, and shoud be false when done fetching`
- `when searchPublicRepositoryByName() succeeds, then publicRepositories should update`
- `when searchPublicRepositoryByName() find no repository, then error should update`
- `when given name is invalid on searchPublicRepositoryByName(), then error should update`

RepositoryPageViewModelTest
- `when updateRepository() with repository, then _repository should update`

#### Testes de UI:

PublicRepositoriesFragmentTest
- `shouldDisplayProgressOnFragmentStart`
- `shouldNotDisplayErrorOnFragmentStart`
- `shouldDisplayRecyclerViewWhenContentIsLoaded`
- `shouldDisplayErrorWhenNoConnection`
- `shouldDisplayProgressOnRepositorySearch`
- `shouldDisplayErrorOnInvalidRepositorySearch`

RepositoryPageFragmentTest
- `shouldDisplayAllViewsOnFragmentStart`

### Inspirações para o design:
https://github.com/Ramotion/reel-search

https://br.pinterest.com/pin/246079567128206277/

https://dribbble.com/shots/5687261-Receive-Payment

https://dribbble.com/shots/9579493-Social-app-screen

