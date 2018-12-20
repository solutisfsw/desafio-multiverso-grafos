package br.com.tassio.grafo.pojo;

import br.com.tassio.grafo.Start;

public class Deslocamento {

	private String origem;
	private String destino;
	private int distancia;

	public Deslocamento(String origem, String destino, int distancia) {
		this.origem = origem;
		this.destino = destino;
		this.distancia = distancia;
	}

	public Vertice getOrigem() {
		return Start.grafo.get(origem);
	}

	public Vertice getDestino() {
		return Start.grafo.get(destino);
	}

	public int getDistancia() {
		return distancia;
	}

	@Override
	public String toString() {
		return origem + "->" + destino + ": " + distancia;
	}

}
