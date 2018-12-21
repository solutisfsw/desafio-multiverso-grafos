/**
@class Graph
@desc Representa um grafo
**/
function Graph(nodes){
	/**@member {Array}**/
	this.nodes = nodes;
}
/**
@name getNodes
@desc Função retorna todos os nós do grafo
@return Nodes[]
*/
Graph.prototype.getNodes = function(){
	return this.nodes;
}

/**
@name getRouteToYPassingX
@desc Função pega as rotas para y passando por x 
@param {array} way - todas as rotas possíveis
@param {node} y - nó alvo
@param {node} x - nó que deve ser visidado
@return Array[][]
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
@return int
*/
Graph.prototype.getCountRouteToYMaxStop = function(way_temp, y, max){
	qtd = 0;
	
	for(var i = 0; i < way_temp[0].length; i++){
		
		if(way_temp[0][i][ way_temp[0][i].length-1 ] != y)
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
@return int
*/
Graph.prototype.getCountRouteToYMaxDistance= function(way_temp, max, y){
	qtd = 0;
	for(var i = 0; i < way_temp[0].length; i++){
		if(way_temp[0][i][way_temp[0][i].length-1] != y)
		  continue;
	  
		if(way_temp[1][i] <= max)
			qtd++;
		
	}
	return qtd;
}

/**
@name  getLessRoute
@desc Função pega as menores rotas
@param {array} way_temp - todas as rotas possíveis
@param {node} x - nó alvo
@return Array[]
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
@return String
*/
Graph.prototype.printRoutes = function(way_temp){
	str="";
	for(var j = 0; j < way_temp[0].length; j++){
		if(way_temp[1][j] == -1)
			continue;
		
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
@return String
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
@return Array[][]
*/
Graph.prototype.getAllRoutes = function(no, no_alvo){
	var no_rest = [];
	var ways = [];
	var r = ways.length;
	
	for(n=0; n < no.getLinks().length; n++){
		ways[r] = [no, no.getLinks()[n].getNode() ];
		
		if(no.getLinks()[n].getNode() != no_alvo)	
			no_rest.push( [n, no.getLinks()[n].getNode() ] );
		
		r++;
	}


	for(var l = 0; l < no_rest.length; l++){
		
		if(no_rest[l] == null)
		   continue;
		
		index_arr = no_rest[l][0];
		no_atual = no_rest[l][1];
		
		for(var m = 0; m < no_atual.getLinks().length; m++){
		
			if(no_atual == no_alvo){
				no_rest[l] = null;
				continue;
			}
			
		
			if(m == 0){
				
				ways[index_arr][ways[index_arr].length-1];
				ways[index_arr].push( no_atual.getLinks()[m].getNode() );

				no_rest[l][1] = no_atual.getLinks()[m].getNode();
				 
			}else if(m > 0){
				
				temp = [];
				for(var p = 0; p < ways[index_arr].length-1; p++)
					temp[p] = ways[ index_arr ][p];
			
				ways.push(temp);
				ways[ways.length-1].push(no_atual.getLinks()[m].getNode());
		
				no_rest.push( [ways.length-1, no_atual.getLinks()[m].getNode()]  );
			
				
			}
			
			
	 
		}
		
		// VERIFICA CICLOS 	
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
			
		if((remove) || (l == no_rest.length-1)){
			l = -1;
			continue;
		}
				
		
		
	}

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
