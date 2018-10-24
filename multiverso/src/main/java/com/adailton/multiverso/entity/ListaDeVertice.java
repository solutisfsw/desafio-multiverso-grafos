package com.adailton.multiverso.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListaDeVertice {
	private List<UniversoVertice> listaVertice = new ArrayList<>();
		
	private UniversoVertice a = new UniversoVertice("a");
	private UniversoVertice b = new UniversoVertice("b");
	private UniversoVertice c = new UniversoVertice("c");
	private UniversoVertice d = new UniversoVertice("d");
	private UniversoVertice e = new UniversoVertice("e");
	
	public List<UniversoVertice> getListaVertice() {
		return listaVertice;
	}
	public void setListaVertice(List<UniversoVertice> listaVertice) {
		this.listaVertice = listaVertice;
	}
	
	public void iniciaVertices() {
		//adicionando as Arestas para cada classe
		a.setRotas(Arrays.asList(new Aresta(70, e),
						new Aresta(50, b),
						new Aresta(50, d) ));

		b.setRotas(Arrays.asList(new Aresta(40, c)));

		c.setRotas(Arrays.asList(new Aresta(40, d),
								new Aresta(20, e)));

		d.setRotas(Arrays.asList(new Aresta(40, c),
								new Aresta(80, e)));
		
		e.setRotas(Arrays.asList(new Aresta(30, b)));
	}
		
	public void iniciaListaVertice() {
		listaVertice.add(a);
		listaVertice.add(b);
		listaVertice.add(c);
		listaVertice.add(d);
		listaVertice.add(e);

	}
			
	public UniversoVertice convertStringVertice(String stringEntrada) {
		
		for(UniversoVertice v:getListaVertice()) {
			if(v.getNome().equalsIgnoreCase(stringEntrada)) {
				return v;				
			}			
		}return null;
	}
		
		
}
