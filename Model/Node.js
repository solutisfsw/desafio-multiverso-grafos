function Node(label){
	this.links = [];
	this.label = label;
}

Node.prototype.getLinks = function(){
	return this.links;
}

Node.prototype.addLink = function(link){
	this.links.push(link);
}

Node.prototype.getLabel= function(){
	return this.label;
}