package com.adailton.multiverso.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListaDeVertice {
	private List<UniversoVertice> listaVertice = new ArrayList<>();
		
	private UniversoVertice a = new UniversoVertice("A");
	private UniversoVertice b = new UniversoVertice("B");
	private UniversoVertice c = new UniversoVertice("C");
	private UniversoVertice d = new UniversoVertice("D");
	private UniversoVertice e = new UniversoVertice("E");
	
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
		
	public void iniciaListaVertice() {//empilhando 
		listaVertice.add(a);
		listaVertice.add(b);
		listaVertice.add(c);
		listaVertice.add(d);
		listaVertice.add(e);

	}
			
	public UniversoVertice convertStringVertice(String stringEntrada) {//procura a vertice que tem o mesmo nome da letra
		
		for(UniversoVertice v:getListaVertice()) {
			if(v.getNome().equalsIgnoreCase(stringEntrada)) {
				return v;				
			}			
		}return null;
	}
	public UniversoVertice getA() {
		return a;
	}
	public void setA(UniversoVertice a) {
		this.a = a;
	}
	public UniversoVertice getB() {
		return b;
	}
	public void setB(UniversoVertice b) {
		this.b = b;
	}
	public UniversoVertice getC() {
		return c;
	}
	public void setC(UniversoVertice c) {
		this.c = c;
	}
	public UniversoVertice getD() {
		return d;
	}
	public void setD(UniversoVertice d) {
		this.d = d;
	}
	public UniversoVertice getE() {
		return e;
	}
	public void setE(UniversoVertice e) {
		this.e = e;
	}
		
		
}
