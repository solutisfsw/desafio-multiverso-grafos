log = 0;

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

