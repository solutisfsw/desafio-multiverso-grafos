function startApp(){
	//Criando os nós
	a = new Node("A");
	b = new Node("B");
	c = new Node("C");
	d = new Node("D");
	e = new Node("E");
	
	//No a
	a.addLink(new Link(b, 50));
	a.addLink(new Link(d, 50));
	a.addLink(new Link(e, 70));
	
	//No b
	b.addLink(new Link(c, 40));
	
	//No c
	c.addLink(new Link(d, 40));
	c.addLink(new Link(e, 20));
	//No d
	d.addLink(new Link(c, 40));
	d.addLink(new Link(e, 80));
	//No e
	e.addLink(new Link(b, 30));
	
	//Grafo com todos os nós
	graph = new Graph([a,b,c,d,e]);
	
	//*****QUESTÕES****
	
	//A distância de A a C passando por B?
	way = Start(a, c);
	str = "";
	way_temp = getRouteToYPassingX(way, c, b);
	document.getElementById("q1").innerHTML =  printRoutes(way_temp) ;
	
	
	//A distância entre A e D?
	way = Start(a, d);
	str = "";
	for(var i = 0; i < way[0].length; i++){
		
		if(way[0][i][ way[0][i].length-1 ] != d)
		  continue;
		
		str += "caminho "+ i + " (";
		for(var j = 0; j < way[0][i].length-1; j++){
			str += way[0][i][j].getLabel()+", ";				
		}
		str += way[0][i][way[0][i].length-1].getLabel();
		str += ") possui a distancia de: "+ way[1][i] + "<br>";
		
		//console.log( str );
	}
	
	
	document.getElementById("q2").innerHTML =  str ;
	
	//A distância de A a C passando por D?
	way = Start(a, c);
	str = "";
	way_temp = getRouteToYPassingX(way, c, d);
	document.getElementById("q3").innerHTML =  printRoutes(way_temp) ;
	
    //O número de rotas saindo de C e voltando a C com no máximo 3 paradas?
	way = Start(c, c);
	qtd = getCountRouteToYMaxStop(way, c, 3);
	document.getElementById("q4").innerHTML = "O número de rotas entre C e C, com no máximo 3 paradas é "+  qtd  ;

	
	
    //A menor rota (em espaço-tempo) entre A e C?
	way = Start(a, c);
	qtd = getCountRouteToYMaxStop(way, c, 4);
	document.getElementById("q5").innerHTML = "O número de rotas entre A e C, com no máximo 3 paradas é "+  qtd  ;

	
    //A menor rota (em espaço-tempo) entre A e C?
	way = Start(a, c);
	menor_rota = getLessRoute(way, c);
	
	str = "A(s) menor(es) rota(s) de A a C é(são): <br>";
	str += printLessRoute(less_route);
	document.getElementById("q6").innerHTML = str;
	
	
     //A menor rota (em espaço-tempo) saindo de B e voltando a B?
	way = Start(b, b);
	less_route = getLessRoute(way, b);
	
	str = "A(s) menor(es) rota(s) de B a B é(são): <br>";
	str += printLessRoute(less_route);
	document.getElementById("q7").innerHTML = str;
	
	
    //O número de diferentes rotas saindo de C e voltando a C com distância máxima de 300 unidades de espaço-tempo?
	way = Start(c, c);
	console.log("Q9 - O número de diferentes rotas saindo de C e voltando a C com distância máxima de 300 unidades de espaço-tempo?");
	qtd = getCountRouteToYMaxDistance(way, 300, c);
	
	str = "O número de rotas entre C e C, com no máximo 300 unidades de espaço-tempo é "+qtd;
	
	document.getElementById("q8").innerHTML = str; 
	
	
}

startApp();



function getRouteToYPassingX (way, y, x){
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

function getCountRouteToYMaxStop(way_temp, y, max){
	qtd = 0;
	
	for(var i = 0; i < way_temp[0].length; i++){
		
		if(way_temp[0][i][ way_temp[0][i].length-1 ] != c)
		  continue;
	  
	  
		if(way_temp[0][i].length <= max+2)
			qtd++;
	}
	
	return qtd;

}


function getCountRouteToYMaxDistance(way_temp, max, x){
	qtd = 0;
	for(var i = 0; i < way[0].length; i++){
		if(way[0][i][way[0][i].length-1] != x)
		  continue;
	  
		if(way[1][i] <= max)
			qtd++;
		
	}
	return qtd;
}

function getLessRoute(way_temp, x){
	
	less_route = [Infinity, []];
	
	for(var i = 0; i < way_temp[0].length-1; i++){
	    console.log(way_temp[1][i], less_route[0])
		
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
	
	
function printRoutes(way_temp){
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


function printLessRoute(less_route){
	str = "";
	for(var l = 0; l < less_route[1].length; l++){
		str += "(";
		console.log(less_route[1][l])
		for(var w = 0; w < less_route[1][l].length-1; w++){
			str += less_route[1][l][w].getLabel()+", ";
		}
		
		str += less_route[1][l][ less_route[1][l].length-1 ].getLabel() +") com peso "+less_route[0]+"<br>";
	}
	
	return str;
}