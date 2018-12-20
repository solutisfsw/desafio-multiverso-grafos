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
	
	way = Start(a, b);
	console.log(way);
	
}

startApp();