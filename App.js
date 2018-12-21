/**
@class App
@desc Responsável por iniciar a aplicação
**/
App = function(){
	/**@member {Graph}**/
	this.graph = null;
};

/**
@name startApp
@desc inicia a aplicação, criando os nós e o grafo
*/
App.prototype.startApp = function(){
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
	this.graph = new Graph([a,b,c,d,e]);
	
	
}

/**
@name playAnswer
@desc exibe na tela todas as respostas do desafio
*/
App.prototype.playAnswer = function(){
	
	//*****QUESTÕES****
	
	//A distância de A a C passando por B?
	way = this.graph.getAllRoutes(a, c);
	str = "";
	way_temp = this.graph.getRouteToYPassingX(way, c, b);
	document.getElementById("q1").innerHTML =   this.graph.printRoutes(way_temp) ;
	
	
	//A distância entre A e D?
	way = this.graph.getAllRoutes(a, d);
	str = this.graph.printRoutes(way);
	
	document.getElementById("q2").innerHTML =  str ;
	
	//A distância de A a C passando por D?
	way = this.graph.getAllRoutes(a, c);
	str = "";
	way_temp = this.graph.getRouteToYPassingX(way, c, d);
	document.getElementById("q3").innerHTML =   this.graph.printRoutes(way_temp) ;
	
    //O número de rotas saindo de C e voltando a C com no máximo 3 paradas?
	way = this.graph.getAllRoutes(c, c);
	qtd = this.graph.getCountRouteToYMaxStop(way, c, 3);
	document.getElementById("q4").innerHTML = "O número de rotas entre C e C, com no máximo 3 paradas é "+  qtd  ;

	
	
    //A menor rota (em espaço-tempo) entre A e C?
	way = this.graph.getAllRoutes(a, c);
	qtd = this.graph.getCountRouteToYMaxStop(way, c, 4);
	document.getElementById("q5").innerHTML = "O número de rotas entre A e C, com no máximo 3 paradas é "+  qtd  ;

	
    //A menor rota (em espaço-tempo) entre A e C?
	way = this.graph.getAllRoutes(a, c);
	menor_rota = this.graph.getLessRoute(way, c);
	
	str = "A(s) menor(es) rota(s) de A a C é(são): <br>";
	str +=  this.graph.printLessRoute(less_route);
	document.getElementById("q6").innerHTML = str;
	
	
     //A menor rota (em espaço-tempo) saindo de B e voltando a B?
	way = this.graph.getAllRoutes(b, b);
	less_route = this.graph.getLessRoute(way, b);
	
	str = "A(s) menor(es) rota(s) de B a B é(são): <br>";
	str +=  this.graph.printLessRoute(less_route);
	document.getElementById("q7").innerHTML = str;
	
	
    //O número de diferentes rotas saindo de C e voltando a C com distância máxima de 300 unidades de espaço-tempo?
	way = this.graph.getAllRoutes(c, c);
	qtd = this.graph.getCountRouteToYMaxDistance(way, 300, c);
	
	str = "O número de rotas entre C e C, com no máximo 300 unidades de espaço-tempo é "+qtd;
	
	document.getElementById("q8").innerHTML = str; 
	
	
}
