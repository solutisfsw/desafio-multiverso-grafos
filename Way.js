function Way(){
	this.links = [];
}

Way.prototype.calcWeigth = function(){
	weigth = 0;
	this.nodes.forEach(function(elem){
		weigth += elem.getWeigth();
	});
	
	return weigth;
}

