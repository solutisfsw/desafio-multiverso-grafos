function Link (end, weight){
	this.end = end;
	this.weight = weight;
}

Link.prototype.getWeight = function(){
	return this.weight;
}

Link.prototype.getNode = function(){
	return this.end;
}

