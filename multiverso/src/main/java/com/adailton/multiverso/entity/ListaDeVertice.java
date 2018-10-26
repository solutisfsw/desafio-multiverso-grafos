package com.adailton.multiverso.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListaDeVertice {
	private List<UniversoVertice> listaVertice = new ArrayList<>();
		
	private UniversoVertice A = new UniversoVertice("A");
	private UniversoVertice B = new UniversoVertice("B");
	private UniversoVertice C = new UniversoVertice("C");
	private UniversoVertice D = new UniversoVertice("D");
	private UniversoVertice E = new UniversoVertice("E");
	
	public List<UniversoVertice> getListaVertice() {
		return listaVertice;
	}
	public void setListaVertice(List<UniversoVertice> listaVertice) {
		this.listaVertice = listaVertice;
	}
	
	public void iniciaVertices() {
		//adicionando as Arestas para cada classe
		A.setRotas(Arrays.asList(new Aresta(70, E),
						new Aresta(50, B),
						new Aresta(50, D) ));

		B.setRotas(Arrays.asList(new Aresta(40, C)));

		C.setRotas(Arrays.asList(new Aresta(40, D),
								new Aresta(20, E)));

		D.setRotas(Arrays.asList(new Aresta(40, C),
								new Aresta(80, E)));
		
		E.setRotas(Arrays.asList(new Aresta(30, B)));
	}
		
	public void iniciaListaVertice() {//empilhando 
		listaVertice.add(A);
		listaVertice.add(B);
		listaVertice.add(C);
		listaVertice.add(D);
		listaVertice.add(E);

	}
			
	public UniversoVertice convertStringVertice(String stringEntrada) {//procura a vertice que tem o mesmo nome da letra
		
		for(UniversoVertice v:getListaVertice()) {
			if(v.getNome().equalsIgnoreCase(stringEntrada)) {
				return v;				
			}			
		}return null;
	}
		
		
}
