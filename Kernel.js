/*DOCUMENTAÇÃO

Descrição geral: este algoritmo vare o nó inicial, verificando se já chegou no destino
ou, caso contrário, se o nó em seguinte tem mais links, armazenando em um vetor para ser
visitado posteriormente. O código faz isso até não restar nós a serem visitados e gera
todos os caminhos possíveis até o nó alvo. 

Way = []
Descrição: way é uma array de arrays que indica um caminho até o nó alvo
exemplo:
wasy [0]  = [A, B, C]
wasy [1]  = [A, D, C]
wasy [2]  = [A, E, B]
-------------------------------

Weigths = []
Descrição: um array para armazenar o peso de cada caminho
Weigths [0] = 180
Weigths [1] = 40
Weigths [2] = 120

------------------------------
no_rest = []
Descrição: é um array de array de duas dimensoes que guarda nós que devem ser visitados.
o primeiro indice é a referênica para o indice do way a qual o nó está vinculados, o segundo
index é o nó.
Ex:
no_rest[0] = [0, C]
no_rest[1] =[1,B]
no_rest[2] =[2,B]

*/

Start = function(no, no_alvo){
	var no_rest = [];
	var ways = [];
	var r = ways.length;

	//é o proprio no
	//if(no == no_alvo)
	//console.log("você já está no nó");
	//else{
		//para todos os links do no inicial
		for(n=0; n < no.getLinks().length; n++){
			//coloca o par = no, um dos links 
			ways[r] = [no, no.getLinks()[n].getNode() ];
			
			//cado exista mais de um link, cadatra como um nó a ser visitado
			if(no.getLinks()[n].getNode() != no_alvo)	
				no_rest.push( [n, no.getLinks()[n].getNode() ] );
			
			r++;
		}

	//}
	
	//controla a deleção lógica
	//qtd_nulls = 0;

	break_count = 0;


	/* agora verifica todos os nós a serem visitados */
	//para todos os rests


	for(var l = 0; l < no_rest.length; l++){
		
		//caso deletado (deleção lógica)
		if(no_rest[l] == null)
		   continue;
		
		
		break_count++
		//impede loop infito
		if(break_count >= 100){
			console.log("break 1");
			break;
		}
		
		//pega o index e o nó
		index_arr = no_rest[l][0];
		no_atual = no_rest[l][1];
		
		//pega todos os links do no_atual
		for(var m = 0; m < no_atual.getLinks().length; m++){
		
			//chegou?
			if(no_atual == no_alvo){
				no_rest[l] = null;
				continue;
			}
			
			
			
			//adiciona o no ao caminho
			if(m == 0){
				
				ways[index_arr][ways[index_arr].length-1];
				ways[index_arr].push( no_atual.getLinks()[m].getNode() );

				no_rest[l][1] = no_atual.getLinks()[m].getNode();
				 
			
			//caso não seja o primeiro link	
			}else if(m > 0){
				//copia o caminho
				temp = [];
				for(var p = 0; p < ways[index_arr].length-1; p++)
					temp[p] = ways[ index_arr ][p];
			
				ways.push(temp);
				ways[ways.length-1].push(no_atual.getLinks()[m].getNode());
		
				no_rest.push( [ways.length-1, no_atual.getLinks()[m].getNode()]  );
			
				
			}
			
			
	 
		}
		
		/** VERIFICA CICLOS ***/	
			var remove = false;
			
			for(var i = 0; i<ways.length; i++)
				for(var j = 0; j<ways[i].length; j++)
					for(var k = j+1; k<ways[i].length; k++){
						if(ways[i][j] == ways[i][k]){
							for(var p = 0; p < no_rest.length; p++)
								if ((no_rest[p] != null) && (no_rest[p][0]==i)){
									no_rest[p] = null;
									remove = true;
								}
									
						}
						
				}
				
			if(remove){
				l = -1;
				continue;
			}
				

				
		//se ocorreu alguma adicao no no_rest, resete o loop
		if(l == no_rest.length-1){
			l = -1;	
		}
		
	}

	//Calcular os pesos
	weights = [];
	for(var i = 0; i < ways.length; i++){
		
		if(ways[i][ways[i].length -1] == no_alvo){
			weights[i] = 0;
			for(var j = 1; j < ways[i].length; j++)
				for(var l = 0; l < ways[i][j-1].getLinks().length; l++)
					if(ways[i][j-1].getLinks()[l].getNode() == ways[i][j])
						weights[i]+= ways[i][j-1].getLinks()[l].getWeight();		
		}else{
			weights[i] = -1;
		}

	}
	
	
	return [ways, weights];

}
