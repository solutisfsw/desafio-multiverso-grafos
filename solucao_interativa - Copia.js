//wasy [0]  = [A, B, C]
//wasy [1]  = [A, D, C]
//wasy [2]  = [A, E, B]

//no_rest = [ [0, C], [1,B], [2,B], [3,E] ]
log = 0;

no_rest = [];
//MAx = Graph.getCountNode();
ways = [];
no = a;
no_alvo = b;
r = 0;
//for(j=0; j< MAX; j++){
r = ways.length;
//é o proprio no
if(no == no_alvo)
	console.log("você já está no nó");
else{
	//para todos os links do no inicial
	for(n=0; n < no.getLinks().length; n++){
		//coloca o par = no, um dos links 
		ways[r] = [no, no.getLinks()[n].getNode() ];
		
		//cado exista mais de um link, cadatra como um nó a ser visitado
		if(no.getLinks()[n].getNode() != no_alvo)	
			no_rest.push( [n, no.getLinks()[n].getNode() ] );
		
		r++;
	}

}

//}


//controla a deleção lógica
qtd_nulls = 0;

/* agora verifica todos os nós a serem visitados */
//para todos os rests

break_count = 0;
clico = false;

for(var l = 0; l < no_rest.length; l++){
	
		//caso deletado
	if(no_rest[l] == null){
	   console.log(l, null);
	   continue;
	}else{
	   console.log(l,no_rest[l]);
	}
	
	//print_rest();
	
	
	break_count++
	if(break_count >= 1000){
		console.log("break 1");
		break;
	}
	

		
	//console.log(l, no_rest.length);
	
	//pega o index e o nó
	index_arr = no_rest[l][0];
	no_atual = no_rest[l][1];
	
	//pega todos os links do no_atual
	for(var m = 0; m < no_atual.getLinks().length; m++){
	
		//chegou?
		if(no_atual == no_alvo){
			console.log("achou em "+m);
			continue;
		}
		
	    //adiciona o no ao caminho
		if(m == 0){
			//verifica se há ciclos. Ex: A B C D C D C ...
			console.log(index_arr, no_atual,  no_atual.getLinks()[m].getNode(),
			ways[index_arr][ways[index_arr].length-1] );
			
			/*if(no_atual.getLinks()[m].getNode() == ways[index_arr][ways[index_arr].length-2]){
	
				for(var g=0; g< no_rest.lenght; g++)
					if(no_rest[g][0] == index_arr) {
						console.log("ciclo");
						console.log(no_rest[g]);
						no_rest[g] = null;
						qtd_nulls++;
					}
				//qtd_nulls++;
				continue;
			
			}else{	*/
			
				ways[index_arr].push( no_atual.getLinks()[m].getNode() );
				//if(no_atual.getLinks()[m].getNode() != no_alvo)	
					no_rest[l][1] = no_atual.getLinks()[m].getNode();
			//}
		
		//caso não seja o primeiro link	
		}else if(m > 0){
			
			//copia o caminho
			temp = [];
			for(var p = 0; p < ways[index_arr].length-1; p++)
				temp[p] = ways[ index_arr ][p];
			/*
			//verifica se há ciclos. Ex: A B C D C D C ...
			//if(no_atual.getLinks()[m].getNode() == ways[index_arr][ways[index_arr].length-2]){
			if(no_atual == ways[index_arr][ways[index_arr].length-1]){
				console.log("CLICO");
				for(g=0; g< no_rest.lenght; g++)
					if(no_rest[g][0] == index_arr) {
						console.log("ciclo");
						console.log(no_rest[g]);
						no_rest[g] = null;
						qtd_nulls++;
					}
				continue;
			
			}else{ */
				ways[ways.length] = temp;
				ways[ways.length-1].push(no_atual.getLinks()[m].getNode());
				no_rest.push( [ways.length-1, no_atual.getLinks()[m].getNode()]  );
			//}
			
		}
		
		
 
	}
	
 
	
	for(var m = 0; m < no_rest.length; m++){
		
			if(no_rest[m] == null){
				continue;
			}else if( (no_rest[m][1] == no_alvo) || (no_rest[m][1] == no) ){
				//console.log(no_rest[m][1] == no_alvo, "Removendo\n");
				//print_rest();
				no_rest[m] = null;
				qtd_nulls++;
				
				if(qtd_nulls >= no_rest.length){
					no_rest = [];
					console.log("break 2");
				}
				
		 
				
			}
	}
	
	if(l == no_rest.length-1){
		l = 0;	
		console.log("reset");
	}//else
		//console.log(no_rest.length, l)
	
	//print_rest();
	
}


function print_rest(str){
	if(str != undefined)
	console.log("\nlog"+str+" - rest")
	else
	console.log("\nlog"+log+" - rest")
	log++;
	no_rest.forEach(function(e){
		if(e != null)
			console.log(e[0]+"  "+e[1].getLabel() );
		else
			console.log("null");
	});
}

function print_ways(){
	console.log("\nlog"+log+" - ways")
	log++;
	ways.forEach(function(e){
			e.forEach(function(e2){
				if(e2 != null)
					console.log(e2.getLabel());
				else
					console.log("null");
			});
			console.log("\n")
	});
}


function count_rest(){
	qtd = 0;
	no_rest.forEach(function(e){
		if(e != null){
			 qtd++;		
		}	
	});
	
	return qtd;
}


console.log(ways);