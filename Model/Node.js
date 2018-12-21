/**
@class Node
@desc Representa um nó do grafo
**/
function Node(label){
	/** @member {Array} **/
	this.links = [];
	/** @member {String} **/
	this.label = label;
}
/**
@name getLinks 
@desc Retorna a lista de links do nó
@return Array 
**/
Node.prototype.getLinks = function(){
	return this.links;
}
/**
@name getLinks 
@desc Adiciona um link ao nó
**/
Node.prototype.addLink = function(link){
	this.links.push(link);
}
/**
@name getLabel
@desc Retorna o label do nó
@return String
**/
Node.prototype.getLabel= function(){
	return this.label;
}