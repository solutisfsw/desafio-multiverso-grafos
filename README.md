#"NOSSO DESAFIO RICK - GRAFOS"
## SOLUÇÃO PROPOSTA 

**Autor:** Adhemar Fontes (adhemarfontes@gmail.com)

**Linguagem:** Python (3.x)

**Dependências:** numpy e pandas. Caso estas bibliotecas não estejam presentes, basta executar o comando:

    python -m pip install --user numpy pandas

##Arquivos

* **rick.py**: código python da solução, seus métodos estão mais detalhados a seguir.

* **main.py**: código python que deverá ser executado para exibir a resposta gerada pela solução para os problemas do desafio. Neste arquivos estão as chamadas referentes a cada um dos problemas.

* **distancias.csv**: arquivo CSV, que para o perfeito funcionamento o código atualmente implementado, deve estar na mesma pasta que o código python. COntém os dados refletindo a organização e distâncias entre os universos. Formato UNIVERSO_ORIGEM(1 caracter),UNIVERSO_DESTINO(1 caracter),DISTANCIA(número inteiro). Fonte de dados usados por main.py e tests.py. 

* **tests.py**: código pyhton para testes unitários.

##API

Foram implementados **dois** métodos para responder aos problemas propostos. Devem ser requisitados através do **import** do arquivo **rick.py**.

* **numeroRotas(distancias, o, d, maxParadas, maxDistancia)**


**Parâmetros**

**distancias**: data frame pandas com a lista de distâncias entre os universos. Formato: O(UNIVERSO_ORIGEM - 1 caracter),D(UNIVERSO_DESTINO - 1 caracter),DT(DISTÂNCIA - número inteiro). Para a implementação, foi utilizado um arquivo CSV para fazer a carga, porém pode-se evoluir para utilização outros meios para obtenção dos dados: input do usuário, webservice, banco de dados.

**o**: universo origem.

**d**: universo destino.

**maxParadas**: máximo de paradas máximas na rota entre a origem e o destino. Para não testar o número de paradas, informar 0 (zero).

**maxDistancia**: distância máxima entre a origem e o destino. Para não testar a distância máxima, informar 0 (zero).

**Retorno**

**Retorno 1**: número de rotas de acordo com os parãmetros informados.

**Retorno 2**: texto com as rotas encontradas, formatadas dentro de colchetes.

* **menorDistancia(distancias, o, d, passandoPor = [])**

**Parâmetros**

**distancias**: data frame pandas com a lista de distâncias entre os universos. Formato: O(UNIVERSO_ORIGEM - 1 caracter),D(UNIVERSO_DESTINO - 1 caracter),DT(DISTÂNCIA - número inteiro). Pode-se utilizar qualquer meio para obtenção dos dados: csv, webservice, banco de dados.

**o**: universo origem.

**d**: universo destino.

**passandoPor**: um array não obrigatório contendo todos os universos que deverão estar presenta na rota buscada.

**Retorno**

**Retorno 1**: número de rotas de acordo com os parãmetros informados.

**Retorno 2**: texto com as rotas encontradas, formatadas dentro de colchetes.

Devido a busca por uma solução mais simples e que possa ser facilmente agregada a uma solução maior, não foi utilizada orientação a objetos, mas sim, programação funcional.

##Código de análise de rotas

No arquivo **rick.py**, estão presentes os métodos **_inicio_ e _buscaRotas_**. 

Estes métodos são responsáveis por localizar as possíveis rotas entre dois universos dados. 

São métodos **genéricos**, pois ao utilizarem **recursividade**, podem resolver os problemas de navegação **independentemente do número de universos e rotas**. 

##Executando o código para solução dos problemas propostos

No prompt de comando executar:

```
python main.py
```

##Executando testes unitários

No prompt de comando executar:

```
python tests.py
```
