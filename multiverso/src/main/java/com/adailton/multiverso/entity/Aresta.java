package com.adailton.multiverso.entity;

public class Aresta {
	private int espaco_tempo;
	private UniversoVertice destino;
	private boolean visitado = false;
	
	
	
	public Aresta(int espaco_tempo, UniversoVertice destino) {
		super();
		this.espaco_tempo = espaco_tempo;
		this.destino = destino;
	}
	public int getespaco_tempo() {
		return espaco_tempo;
	}
	public void setespaco_tempo(int espaco_tempo) {
		this.espaco_tempo = espaco_tempo;
	}
	public UniversoVertice getDestino() {
		return destino;
	}
	public void setDestino(UniversoVertice destino) {
		this.destino = destino;
	}
	public boolean getVisitado() {
		return visitado;
	}
	public void setVisitado(boolean visitado) {
		this.visitado = visitado;
	}
	
	
	
	
	
}
