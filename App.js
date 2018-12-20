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
	console.log("Q1 - A distância de A a C passando por B?");
	for(var i = 0; i < way[0].length; i++){
		for(var j = 0; j < way[0][i].length; j++){
			if(way[0][i][j] == b){
				var str = "caminho "+ i + " (";
				for(var l = 0; l < way[0][i].length-1; l++){
					str += way[0][i][l].getLabel()+", ";
				}
				str += way[0][i][way[0][i].length-1].getLabel();
				
				str += ") possui a distancia de: "+ way[1][i];
				
				console.log( str );
			}
		}
	}
	//A distância entre A e D?
	way = Start(a, d);

	console.log("Q2 - A distância entre A e D?");
	for(var i = 0; i < way[0].length; i++){
		
		if(way[0][i][ way[0][i].length-1 ] != d)
		  continue;

		for(var j = 0; j < way[0][i].length; j++){
				var str = "caminho "+ i + " (";
				for(var l = 0; l < way[0][i].length-1; l++){
					str += way[0][i][l].getLabel()+", ";
				}
				str += way[0][i][way[0][i].length-1].getLabel();
				
				str += ") possui a distancia de: "+ way[1][i];
				
				
			
		}
		
		console.log( str );
	}
	
	
	
	
	//A distância de A a C passando por B?
	way = Start(a, c);
	console.log("Q3 - A distância de A a C passando por D?");
	for(var i = 0; i < way[0].length; i++){
		
		for(var j = 0; j < way[0][i].length; j++){
			if(way[0][i][j] == b){
				var str = "caminho "+ i + " (";
				for(var l = 0; l < way[0][i].length-1; l++){
					str += way[0][i][l].getLabel()+", ";
				}
				str += way[0][i][way[0][i].length-1].getLabel();
				
				str += ") possui a distancia de: "+ way[1][i];
				
				console.log( str );
			}
		}
	}
	
	
    //A distância de A a C passando por D?
	way = Start(a, c);
	console.log("Q4 - A distância de A a C passando por D?");
	
	for(var i = 0; i < way[0].length; i++){
		for(var j = 0; j < way[0][i].length; j++){
			if(way[0][i][j] == d){
				var str = "caminho "+ i + " (";
				for(var l = 0; l < way[0][i].length-1; l++){
					str += way[0][i][l].getLabel()+", ";
				}
				str += way[0][i][way[0][i].length-1].getLabel();
				
				str += ") possui a distancia de: "+ way[1][i];
				
				console.log( str );
			}
		}
	}	
	
    //O número de rotas saindo de C e voltando a C com no máximo 3 paradas?
	way = Start(c, c);
	console.log("Q5 - O número de rotas saindo de C e voltando a C com no máximo 3 paradas?");	
	qtd = 0;
	
	for(var i = 0; i < way[0].length; i++){
		
		if(way[0][i][ way[0][i].length-1 ] != c)
		  continue;
	  
	  
		if(way[0][i].length <= 3+2)
			qtd++;
	}
	
	console.log("O número de rotas entre C e C, com no máximo 3 paradas é "+qtd);
	
	
    //O número de rotas entre A e C com no máximo 4 paradas?
	way = Start(a, c);
	console.log("Q6 - O número de rotas entre A e C com no máximo 4 paradas?");	
	qtd = 0;
	
	for(var i = 0; i < way[0].length; i++){
		
		if(way[0][i][ way[0][i].length-1 ] != c)
		  continue;
	  
	  
		if(way[0][i].length <= 4 + 2)
			qtd++;
	}
	
	console.log("O número de rotas entre A e C, com no máximo 4 paradas é "+qtd);
	
    //A menor rota (em espaço-tempo) entre A e C?
	way = Start(a, c);
	console.log("Q7 - A menor rota (em espaço-tempo) entre A e C?");
	menor_rota = [Infinity, null];
	for(var i = 0; i < way[0].length-1; i++){
	
		if(way[0][i][way[0][i].length-1] != c)
		  continue;
		
		if(way[1][i] < menor_rota[0]){
			menor_rota = [way[1][i], way[0][i] ];
		}
		
	}
	
	str = "A menor rota de A a C é (";
	for(var l = 0; l < menor_rota[1].length-1; l++){
		str += menor_rota[1][l].getLabel()+", ";
	}
	
	str += menor_rota[1][menor_rota[1].length-1].getLabel();
	
	console.log(str + ") com peso "+menor_rota[0]);
	
	
    //A menor rota (em espaço-tempo) saindo de B e voltando a B?
	way = Start(b, b);
	console.log("Q8 - A menor rota (em espaço-tempo) saindo de B e voltando a B?");	
	menor_rota = [Infinity, null];
	for(var i = 0; i < way[0].length-1; i++){
	
		if(way[0][i][way[0][i].length-1] != b)
		  continue;
		
		if(way[1][i] < menor_rota[0]){
			menor_rota = [way[1][i], way[0][i] ];
		}
		
	}
	
	str = "A menor rota de B a B é (";
	for(var l = 0; l < menor_rota[1].length-1; l++){
		str += menor_rota[1][l].getLabel()+", ";
	}
	
	str += menor_rota[1][menor_rota[1].length-1].getLabel();
	
	console.log(str + ") com peso "+menor_rota[0]);
	
    //O número de diferentes rotas saindo de C e voltando a C com distância máxima de 300 unidades de espaço-tempo?
	way = Start(c, c);
	console.log("Q9 - O número de diferentes rotas saindo de C e voltando a C com distância máxima de 300 unidades de espaço-tempo?");
	qtd = 0;
	for(var i = 0; i < way[0].length; i++){
		if(way[0][i][way[0][i].length-1] != c)
		  continue;
		if(way[1][i] <= 300)
			qtd++;
		
	}
	console.log("O número de rotas entre C e C, com no máximo 300 unidades de espaço-tempo é "+qtd);
	
	//console.log(way);
	
}

startApp();