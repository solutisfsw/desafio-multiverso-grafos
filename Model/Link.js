/**
@class Link
@desc Representa uma aresta ponderada do grafo
**/
function Link (end, weight){
	/** @member {Node} **/
	this.end = end;
	/** @member {int} **/
	this.weight = weight;
}
/**
@name getWeight
@desc Retorna o peso da aresta
@return int
**/
Link.prototype.getWeight = function(){
	return this.weight;
}
/**
@name getNode
@desc Retorna o nó que a aresta faz a ligação
@return Node
**/
Link.prototype.getNode = function(){
	return this.end;
}

