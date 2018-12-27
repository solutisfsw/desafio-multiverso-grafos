package com.solutis.desafio.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.solutis.desafio.model.Grafo;
import com.solutis.desafio.model.Vertice;

public class CalcularDistancias {

	public CalcularDistancias(Grafo grafo) {
		Set<Vertice> vertices = grafo.getVertices();
		for (Vertice v : vertices) {
			v.setDistancia(0);
		}
	}

	// questao5 /4 /8
	public List<List<Vertice>> getRotasEntreDoisVerticesComXParadas(Vertice vInicio, Vertice vfim, int maximoParada) {
		List<Vertice> listVisitados = new ArrayList<>();
		List<List<Vertice>> caminhos = new ArrayList<>();
		caminhos = getListVisitados(vInicio, vfim, listVisitados, maximoParada, caminhos);
		return caminhos;
	}

	// questao8
	public Map<List<Vertice>, Integer> getRotasEntreDoisVerticesComMaximoXPesos(Vertice vInicio, Vertice vfim, int maxPesos) {
		limparVerticeInicio(vInicio);
		List<Vertice> listVisitados = new ArrayList<>();
		Map<List<Vertice>, Integer> caminhos = new HashMap<>();
		caminhos = getListVisitados(vInicio, vfim, listVisitados, caminhos);

		Map<List<Vertice>, Integer> listValores = new HashMap<>();
		for (Entry<List<Vertice>, Integer> elemento : caminhos.entrySet()) {
			Integer value = elemento.getValue();
			if (value <= maxPesos)
				listValores.put(elemento.getKey(), value);
		}
		return listValores;
	}

	private static void limparVerticeInicio(Vertice vInicio) {
		vInicio.setDistancia(0);
		vInicio.setCaminho(Collections.emptyList());
	}

	// questao8
	private Map<List<Vertice>, Integer> getListVisitados(Vertice vCorrente, Vertice vfim, List<Vertice> listVisitados,
			Map<List<Vertice>, Integer> caminhos) {

		if (vCorrente.equals(vfim) && (listVisitados.size() > 0)) {
			caminhos.put(new ArrayList<>(listVisitados), 0);
			return caminhos;
		}

		for (Entry<Vertice, Integer> elemento : vCorrente.getVerticesVisinhos().entrySet()) {
			Vertice verticeAtual = elemento.getKey();
			Integer peso = elemento.getValue();

			if (!listVisitados.contains(verticeAtual)) {
				listVisitados.add(verticeAtual);
				Integer calcularMenorDistancia = calcularPeso(verticeAtual, peso, vCorrente);
				getListVisitados(verticeAtual, vfim, listVisitados, caminhos);
				if (caminhos.containsKey(listVisitados)) {
					caminhos.put(new ArrayList<>(listVisitados), calcularMenorDistancia);
				}
				listVisitados.remove(verticeAtual);
				limparVerticeInicio(verticeAtual);
			}
		}
		listVisitados.remove(vCorrente);
		return caminhos;
	}

	// questao8
	private Integer calcularPeso(Vertice verticeVizinho, Integer valorDoPeso, Vertice verticeComMenorDistancia) {
		int distancia = verticeComMenorDistancia.getDistancia();
		int total = distancia + valorDoPeso;
		verticeVizinho.setDistancia(total);
		return total;
	}

	/// questao4
	private List<List<Vertice>> getListVisitados(Vertice vCorrente, Vertice vfim, List<Vertice> listVisitados, int maximoParada,
			List<List<Vertice>> rotas) {

		if (vCorrente.equals(vfim) && (listVisitados.size() > 0 && listVisitados.size() <= maximoParada)) {
			rotas.add(new ArrayList<>(listVisitados));
			return rotas;
		}

		for (Entry<Vertice, Integer> elemento : vCorrente.getVerticesVisinhos().entrySet()) {
			Vertice verticeAtual = elemento.getKey();

			if (!listVisitados.contains(verticeAtual)) {
				listVisitados.add(verticeAtual);
				getListVisitados(verticeAtual, vfim, listVisitados, maximoParada, rotas);
				listVisitados.remove(verticeAtual);
			}
		}
		listVisitados.remove(vCorrente);
		return rotas;
	}
}
