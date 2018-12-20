function startApp(){
	
	a = new Node("A");
	b = new Node("B");
	c = new Node("C");
	d = new Node("D");
	e = new Node("E");
	
	//a
	a.addLink(new Link(b, 50));
	a.addLink(new Link(d, 50));
	a.addLink(new Link(e, 70));
	
	//b
	b.addLink(new Link(c, 40));
	
	//c
	c.addLink(new Link(d, 40));
	c.addLink(new Link(e, 20));
	//d
	d.addLink(new Link(c, 40));
	d.addLink(new Link(e, 80));
	//e
	e.addLink(new Link(b, 30));
	
	graph = new Graph([a,b,c,d,e]);
	
	
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
				
				//for(var l = 0; l < way[0][i].length-1; l++){
					str += way[0][i][j].getLabel()+", ";
				//}
				//				
		}
		str += way[0][i][way[0][i].length-1].getLabel();
		str += ") possui a distancia de: "+ way[1][i] + "<br>";
		
		//console.log( str );
	}
	
	
	document.getElementById("q2").innerHTML =  str ;
	
	//A distância de A a C passando por D?
	way = Start(a, c);
	console.log("Q3 - A distância de A a C passando por D?");
	str = "";
	way_temp = getRouteToYPassingX(way, c, b);
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
	
	str = "A menor rota de A a C é (";
	for(var l = 0; l < menor_rota[1].length-1; l++){
		str += menor_rota[1][l].getLabel()+", ";
	}
	
	str += menor_rota[1][menor_rota[1].length-1].getLabel() +") com peso "+menor_rota[0];
	
	document.getElementById("q6").innerHTML = str;
	
	
     //A menor rota (em espaço-tempo) saindo de B e voltando a B?
	way = Start(b, b);
	menor_rota = getLessRoute(way, b);
	console.log(menor_rota)
	
	str = "A menor rota de B a B é (";
	for(var l = 0; l < menor_rota[1].length-1; l++){
		str += menor_rota[1][l].getLabel()+", ";
	}
	
	str += menor_rota[1][menor_rota[1].length-1].getLabel() +") com peso "+menor_rota[0];
	
	document.getElementById("q7").innerHTML = str;
	
	
	
	
    //O número de diferentes rotas saindo de C e voltando a C com distância máxima de 300 unidades de espaço-tempo?
	way = Start(c, c);
	console.log("Q9 - O número de diferentes rotas saindo de C e voltando a C com distância máxima de 300 unidades de espaço-tempo?");
	qtd = getCountRouteToYMaxDistance(way, 300, c);
	
	str = "O número de rotas entre C e C, com no máximo 300 unidades de espaço-tempo é "+qtd;
	
	document.getElementById("q8").innerHTML = str; 
	
	//console.log(way);
	
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
	
	less_route = [Infinity, null];
	
	for(var i = 0; i < way_temp[0].length-1; i++){
	   
		if(way_temp[0][i][ way_temp[0][i].length-1 ] != x)
		  continue;
	  
		if(way_temp[1][i] < less_route[0]){
			less_route = [way_temp[1][i], way_temp[0][i] ];
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