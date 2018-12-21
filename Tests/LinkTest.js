//Teste unitários Link
n = new Node();
mylink = new Link(n, 10);
//instanciar
assert( mylink instanceof Link, "erro ao instanciar Link");
assert( mylink.getNode() == n, "erro ao atribuir o node");
assert( mylink.getWeight() == 10, "erro ao atribuir o p");

console.log("Teste Link - 100%");