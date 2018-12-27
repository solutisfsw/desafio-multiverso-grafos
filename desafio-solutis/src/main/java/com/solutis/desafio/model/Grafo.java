package com.solutis.desafio.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class Grafo {

	private Set<Vertice> vertices = new HashSet<>();

	public Grafo() {
	}

	public void adicionarVertice(Set<Vertice> listadeVertices) {
		this.setVertices(listadeVertices);

	}

	public Set<Vertice> getVertices() {
		return vertices;
	}

	public void setVertices(Set<Vertice> vertices) {
		this.vertices = vertices;
	}
}
