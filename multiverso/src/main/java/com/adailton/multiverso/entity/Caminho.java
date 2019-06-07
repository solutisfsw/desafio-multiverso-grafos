package com.adailton.multiverso.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Caminho {

	private List<String> letrasDoCaminho = new ArrayList<>();
	private int qtdParada  ;
	private int totalEspacoTempo=0;
	private boolean caminhoValido = false;
	private String menorCaminho="";
	
	
	
	
	
	public List<String> getLetrasDoCaminho() {
		return letrasDoCaminho;
	}
	public void setletrasDoCaminho(String letra) {
		this.letrasDoCaminho.add(letra);
	}
	
	public int getQtdParada() {
		return qtdParada;
	}
	public void setQtdParada(int qtdParada) {
		this.qtdParada = getLetrasDoCaminho().size();// qtdParada vai ser sempre o tamanho da lista de LetrasdoCaminho
	}
	public int getTotalEspacoTempo() {
		return totalEspacoTempo;
	}
	public void setTotalEspacoTempo(int totalEspacoTempo) {
		this.totalEspacoTempo = totalEspacoTempo;
	}
	public boolean isCaminhoValido() {
		return caminhoValido;
	}
	public void setCaminhoValido(boolean caminhoValido) {
		this.caminhoValido = caminhoValido;
	}
	
	public void somaTotalEP(int espacoTempo) {//para contabilizar a distancia total
		setTotalEspacoTempo(getTotalEspacoTempo() + espacoTempo);
	}
	public String getMenorCaminho() {
		return menorCaminho;
	}
	public void setMenorCaminho(String menorCaminho) {
		this.menorCaminho = menorCaminho;
	}
	
	
	
	
}
