//Teste unitários Link
n1 = new Node("A");
n2 = new Node("B");
n3 = new Node("C");

n1.addLink(n2);
n2.addLink(n1);
n3.addLink(n1);


graph = new Graph([n1, n2, n3]);


console.log("Teste Graph - 100%");