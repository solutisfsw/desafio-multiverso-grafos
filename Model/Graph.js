function Graph(nodes){
	this.nodes = nodes;
}

/**
@name getRouteToYPassingX
@desc Função pega as rotas para y passando por x 
@param {array} way - todas as rotas possíveis
@param {node} y - nó alvo
@param {node} x - nó que deve ser visidado
*/
Graph.prototype.getRouteToYPassingX = function(way, y, x){
	way_temp = [[],[]];
	for(var i = 0; i < way[0].length; i++){	
		
		if(way[0][i][ way[0][i].length-1 ] != y)
		  continue;
	  
		for(var j = 0; j < way[0][i].length; j++){
			if(way[0][i][j] == x){
				way_temp[0].push(way[0][i]);
				way_temp[1].push(way[1][i]);
				continue;
			}
		}
	}	

	return way_temp;	
}

/**
@name getCountRouteToYMaxStop
@desc Função conta quantas rotas existem com no maximo x paradas
@param {array} way_temp - todas as rotas possíveis
@param {node} y - nó alvo
@param {node} max - limite de paradas
*/
Graph.prototype.getCountRouteToYMaxStop = function(way_temp, y, max){
	qtd = 0;
	
	for(var i = 0; i < way_temp[0].length; i++){
		
		if(way_temp[0][i][ way_temp[0][i].length-1 ] != c)
		  continue;
	  
	  
		if(way_temp[0][i].length <= max+2)
			qtd++;
	}
	
	return qtd;

}

/**
@name getCountRouteToYMaxDistance
@desc Função pega rotas com limite de distância
@param {array} way_temp - todas as rotas possíveis
@param {node} y - nó alvo
@param {node} max - limite de paradas
*/
Graph.prototype.getCountRouteToYMaxDistance= function(way_temp, max, y){
	qtd = 0;
	for(var i = 0; i < way[0].length; i++){
		if(way[0][i][way[0][i].length-1] != y)
		  continue;
	  
		if(way[1][i] <= max)
			qtd++;
		
	}
	return qtd;
}

/**
@name  getLessRoute
@desc Função pega as menores rotas
@param {array} way_temp - todas as rotas possíveis
@param {node} y - nó alvo
*/
Graph.prototype.getLessRoute = function(way_temp, x){
	
	less_route = [Infinity, []];
	
	for(var i = 0; i < way_temp[0].length-1; i++){
	  
		if(way_temp[0][i][ way_temp[0][i].length-1 ] != x)
		  continue;
	  
		if(way_temp[1][i] < less_route[0]){
			
			less_route = [way_temp[1][i], [ way_temp[0][i] ] ];
		
		}else if (way_temp[1][i] == less_route[0]){
			
			less_route[1].push(way_temp[0][i]);
			
			
		}
		
	}
	
	return less_route;
	
}
	
/**
@name printRoutes
@desc Função printa rotas
@param {array} way_temp - rotas
*/
Graph.prototype.printRoutes = function(way_temp){
	str="";
	for(var j = 0; j < way_temp[0].length; j++){
		str += "caminho "+ j + " (";
			
			for(var l = 0; l < way_temp[0][j].length-1; l++){
				str += way_temp[0][j][l].getLabel()+", ";
			}
			
			str += way_temp[0][j][way_temp[0][j].length-1].getLabel();
		
		
		str += ") possui a distancia de: "+ way_temp[1][j] +"<br>";
	}
	
	return str;
}

/**
@name printLessRoute
@desc Função printa rotas
@param {array} less_route- rotas
*/
Graph.prototype.printLessRoute = function(less_route){
	str = "";
	for(var l = 0; l < less_route[1].length; l++){
		str += "(";
		for(var w = 0; w < less_route[1][l].length-1; w++){
			str += less_route[1][l][w].getLabel()+", ";
		}
		
		str += less_route[1][l][ less_route[1][l].length-1 ].getLabel() +") com peso "+less_route[0]+"<br>";
	}
	
	return str;
}


/**
@name getAllRoutes
@desc Método retorna todas as rotas de um no a outro
@param {Node} no - nó inicial
@param {array} less_route- rotas
*/
Graph.prototype.getAllRoutes = function(no, no_alvo){
	var no_rest = [];
	var ways = [];
	var r = ways.length;
	
	//para todos os links do no inicial
	for(n=0; n < no.getLinks().length; n++){
		//coloca o par = no, um dos links 
		ways[r] = [no, no.getLinks()[n].getNode() ];
		
		//cado exista mais de um link, cadatra como um nó a ser visitado
		if(no.getLinks()[n].getNode() != no_alvo)	
			no_rest.push( [n, no.getLinks()[n].getNode() ] );
		
		r++;
	}

	/* agora verifica todos os nós a serem visitados */
	//para todos os rests
	for(var l = 0; l < no_rest.length; l++){
		
		//caso deletado (deleção lógica)
		if(no_rest[l] == null)
		   continue;
		
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
