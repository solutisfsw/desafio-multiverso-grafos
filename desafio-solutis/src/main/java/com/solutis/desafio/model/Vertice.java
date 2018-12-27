package com.solutis.desafio.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Vertice {

	private String nome;

	private int distancia = Integer.MAX_VALUE;

	private Map<Vertice, Integer> verticesVisinhos = new HashMap<>();

	private List<Vertice> caminho = new LinkedList<>();

	public Vertice(String nome) {
		this.nome = nome;
	}

	public Vertice() {
		// TODO Auto-generated constructor stub
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getDistancia() {
		return distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}

	public Map<Vertice, Integer> getVerticesVisinhos() {
		return verticesVisinhos;
	}

	public void setVerticesVisinhos(Map<Vertice, Integer> verticesVisinhos) {
		this.verticesVisinhos = verticesVisinhos;
	}

	public void adcionarDistanciaAosVisinhos(Vertice vertice, int distancia) {
		verticesVisinhos.put(vertice, distancia);

	}

	public List<Vertice> getCaminho() {
		return caminho;
	}

	public void setCaminho(List<Vertice> caminho) {
		this.caminho = caminho;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertice other = (Vertice) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	
}
