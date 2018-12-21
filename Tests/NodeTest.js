//Teste unitários Nodes

no = new Node("A");
//instanciar
assert( no instanceof Node, "erro ao instanciar Node");
//adicionar links
no.addLink(new Link());
no.addLink(new Link());
no.addLink(new Link());
assert( no.getLinks().length == 3, "erro ao inserir link no Node");
//obter o label
assert( no.getLabel() == "A", "erro ao obter o label do Node");
console.log("Teste Node - 100%");