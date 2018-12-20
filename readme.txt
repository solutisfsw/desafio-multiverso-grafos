==================================================================================================================================================================
Desenvolvedor
==================================================================================================================================================================
Nome: Tassio Coelho
E-mail: tassio@tassio.com.br
Telefone: (71) 98834-5905


==================================================================================================================================================================
Definições Gerais
==================================================================================================================================================================

As nomenclaturas dentro do projeto foram baseadas nas definições dos seguintes conceitos:

- Vértice: É um ponto no grafo que possui uma ou mais Rotas para outros Vértices.

- Rota: Com base em um determinado Vértice, é a menor distância que o liga diretamente a outro Vértice. Basicamente as ligações do grafo.

- Deslocamento: Representa a ação de se deslocar diretamente entre um Vértice e outro por uma determinada distância para compor um caminho.

- Caminho: É um conjunto de Deslocamentos que descreve o caminho a ser percorrido entre o Vértice de origem e o Vértice de destino solicitado pelo problema.

- Requisição de Caminho: É uma solicitação formal baseada em um problema onde deseja-se partir de uma origem até um destino respeitando as definições do problema, por exemplo, no máximo 3 paradas.

- Solução: É o conjunto de Caminhos que satisfazem as condições impostas pelo problema.



==================================================================================================================================================================
Arquivos Necessários
==================================================================================================================================================================

Para execução desta aplicação é necessário ter dois arquivos JSON que estão na raiz do projeto.

1 - grafo.json

	Este arquivo contém a definição formal do grafo. A estrutura dele corresponde exatamente a um HashMap<String, Vertice>.
	O arquivo pode ser alterado a vontade sempre respeitando a estrutura.
	A estrutura atual dele corresponde exatamente ao grafo do desafio.

2 - requisicao.json

	Este arquivo contém a definição formal de quais soluções a aplicação precisa encontrar. A estrutura dele corresponde exatamente a um List<RequisicaoCaminho>.
	O arquivo pode ser alterado a vontade sempre respeitando a estrutura.
	A estrutura atual dele corresponde exatamente aos 8 problemas propostos pelo desafio.
	
	Uma requisição possui os seguintes atributos:
	
		- Origem: Vértice de onde se deseja partir.
		
		- Destino: Vértice que se deseja chegar.
		
		- Lista de vértices obrigatórios: São vértices que obrigatoriamente precisam ser contemplados para que o caminho seja aceito como solução.
			                              ATENÇÃO: Os caminhos que não passam pelos vértices obrigatórios são descartados.
										  
		- Quantidade máxima de paradas: Determina a quantidade máxima de paradas que um caminho pode ter para que seja aceito como solução.
										Para não usar esta restrição informar -1.
		                                ATENÇÃO: Os caminhos que possuem uma quantidade de paradas maior que o permitido são descartados.
										OBSERVAÇÃO: É considerado como parada apenas vértices que não são origem ou destino do problema e que estão no caminho.
																
		- Distância máxima do caminho: Determina a distância máxima que o caminho deve possuir.
									   Para não usar esta restrição informar -1.
		                               ATENÇÃO: Os caminhos que possuem uma distância maior que a permitida são descartados.
									   
		- Tipo de Caminho: Em alguns problemas propostos pelo desafio é especificado que deseja-se o menor caminho possível ("menor rota em espaço-tempo").
						   Outros perguntam qual a distância entre dois vértices, mas não especificam se é o menor possível ou qualquer um que satisfaça as condições.
						   E outros pedem especificamente todos os caminhos possíveis, ou a quantidade de caminhos possíveis.
						   
						   Com base nessa análise o atributo "tipoCaminho" de uma requisição aceita os seguintes valores:
						   
						   TODOS: Todos os caminhos que satisfazem as condições do problema serão retornados como solução.
						   MENOR_POSSIVEL: Esse tipo possui o mesmo custo computacional que o tipo "TODOS", porém ele retorna como solução apenas o menor caminho.
						   QUALQUER: Esse tipo visa diminuir o custo computacional. Ao encontrar o primeiro caminho que satisfaz o problema a requisição é interrompida.
	


==================================================================================================================================================================
Build, Execução e Solução
==================================================================================================================================================================

- Build: O projeto foi desenvolvido com maven. Basta executar o comando "mvn clean install" na pasta do projeto.

- Execução:

			java -jar [nome_do_jar] [caminho_json_grafo] [caminho_json_requisicao] [caminho_pasta_solucao]

			[nome_do_jar]: Nome do jar desta aplicação.
			[caminho_json_grafo]: Caminho completo do arquivo json que contem a definição formal do grafo, incluindo o nome do arquivo. Ex: grafo.json
			[caminho_json_requisicao]: Caminho completo do arquivo json que contem as requisições das rotas desejadas, incluindo o nome do arquivo. Ex: requisicao.json
			[caminho_pasta_solucao]: Caminho completo da pasta onde as soluções das requisições devem ser salvas.
			
- Solução: As soluções serão armazenadas em arquivos json dentro da pasta [caminho_pasta_solucao] no formato solucao-TIMESTAMP.json
