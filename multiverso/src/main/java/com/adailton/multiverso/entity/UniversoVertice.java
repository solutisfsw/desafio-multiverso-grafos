package com.adailton.multiverso.entity;

import java.util.ArrayList;
import java.util.List;

public class UniversoVertice {
	private String nome;
	private List<Aresta> rotas = new ArrayList<>();//apontando para as vertices
	
	
	
	
	public UniversoVertice() {
		super();
	}

	public UniversoVertice(String nome) {
		super();
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<Aresta> getRotas() {
		return rotas;
	}
	
	public void setRotas(List<Aresta> rotas) {
		this.rotas = rotas;
	}
	
	
	
}
