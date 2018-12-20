package br.com.tassio.grafo.pojo;

import java.util.ArrayList;
import java.util.List;

public class Caminho implements Cloneable, Comparable<Caminho> {

	private int distanciaTotal = 0;
	private List<Deslocamento> listaDeslocamento = new ArrayList<Deslocamento>();

	public List<Deslocamento> getListaDeslocamento() {
		return listaDeslocamento;
	}

	public void adicionarDeslocamento(Deslocamento deslocamento) {
		listaDeslocamento.add(deslocamento);
		calcularDistanciaTotal();
	}

	public Caminho clone() {

		Caminho novo = new Caminho();

		for (Deslocamento d : this.listaDeslocamento) {
			novo.listaDeslocamento.add(d);
		}
		return novo;

	}

	public void calcularDistanciaTotal() {

		distanciaTotal = 0;
		for (Deslocamento deslocamento : listaDeslocamento) {
			distanciaTotal += deslocamento.getDistancia();
		}
	}

	public int getDistanciaTotal() {
		return distanciaTotal;
	}

	public int compareTo(Caminho outroCaminho) {

		if (this.getDistanciaTotal() < outroCaminho.getDistanciaTotal()) {

			return -1;

		} else if (this.getDistanciaTotal() == outroCaminho.getDistanciaTotal()) {

			if (this.listaDeslocamento.size() < outroCaminho.getListaDeslocamento().size()) {
				return -1;
			} else if (this.listaDeslocamento.size() == outroCaminho.getListaDeslocamento().size()) {
				return 0;
			} else {
				return 1;
			}

		} else {
			return 1;
		}

	}

}
