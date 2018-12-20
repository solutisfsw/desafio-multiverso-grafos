function Graph(nodes){
	this.nodes = nodes;
	this.countlinks; 
	
}

Graph.prototype.toNode = function(s, e){

	console.log( graph.findAllWays2(s, e, [] , i, null,0));

} 


Graph.prototype.findAllWays2 = function(start, end, arr , n, prior, c, i){
   
   if(arr.length < 5)
		arr.push(findAllWays2(start, end, arr , n, prior, c, i))

   
}



Graph.prototype.findAllWays = function(start, end, w, n, prior, c){
	
	/*qtd++;
	
	if(qtd > 5)
		return -1;*/
	
	links = start.getLinks();
	//console.log( start, links[n].getNode(), prior, links[n].getNode() == prior);
	if(w.length == 0)
		w[0] = 0;
	
	w.push(start.getLabel())
	
	if( (links.length != 0) && (start != end)){
			
			if((links[n].getNode() != prior)){
				
				w[0]+=links[n].getWeight();
				return this.findAllWays(links[n].getNode(), end,  w, 0,  start, c );
			}else{
				w[0]+=links[n+1].getWeight();
				return this.findAllWays(links[n+1].getNode(), end,   w, 0, start );
			}
	}else{ 
		return  w;
	}
	

}


Graph.prototype.showNodes = function(){
	console.log("SHOW NODES");
	this.nodes.forEach(function(element){
		console.log("Node: "+element.getLabel());
		var links = element.getLinks();
		console.log("links");
		links.forEach(function(element){
			console.log(element.getNode().getLabel() + "-" +element.getWeight());
		});
		console.log("\n");
		
	});
		
	
}

Graph.prototype.getCountNode = function(){
	return this.nodes.length; 
}