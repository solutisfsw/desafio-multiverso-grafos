package com.solutis.desafio.service;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Set;

import com.solutis.desafio.model.Grafo;
import com.solutis.desafio.model.Vertice;

public class MenorCaminho {

	private Set<Vertice> verticesVisitados;
	private Set<Vertice> verticesNaoVisitados;

	// Percorre todos os vertices e descobri o menor caminho
	public MenorCaminho(Grafo grafo, Vertice vInicio) {
		iniciarDistancias(grafo);
		limparVerticeInicio(vInicio);
		verticesVisitados = new HashSet<>();
		verticesNaoVisitados = new HashSet<>();

		verticesNaoVisitados.add(vInicio);

		while (verticesNaoVisitados.size() > 0) {
			Vertice verticeComMenorDistancia = getVerticeMenorDistancia(verticesNaoVisitados);
			for (Entry<Vertice, Integer> visinho : verticeComMenorDistancia.getVerticesVisinhos().entrySet()) {

				Vertice verticeVizinho = visinho.getKey();
				Integer valorDoPeso = visinho.getValue();

				if (!verticesVisitados.contains(verticeVizinho)) {
					calcularMenorDistancia(verticeVizinho, valorDoPeso, verticeComMenorDistancia);
					verticesNaoVisitados.add(verticeVizinho);
				} else if (verticeVizinho.equals(vInicio)) {
					if (verticeVizinho.getDistancia() == 0) {
						verticeVizinho.setDistancia(verticeComMenorDistancia.getDistancia() + valorDoPeso);
						LinkedList<Vertice> listaDeCaminhos = null;
						listaDeCaminhos = new LinkedList<>(verticeComMenorDistancia.getCaminho());
						listaDeCaminhos.add(verticeComMenorDistancia);
						verticeVizinho.setCaminho(listaDeCaminhos);
					}
				}
			}
			verticesVisitados.add(verticeComMenorDistancia);
			verticesNaoVisitados.remove(verticeComMenorDistancia);
		}
	}

	public String buscarMenorRotaIniciandoEm_B_Finalizando_B(Vertice vertice) {
		vertice.getCaminho().add(vertice);
		String caminho = "";
		for (Vertice vert : vertice.getCaminho()) {
			caminho = caminho + vert.getNome();
		}
		return caminho + " ";
	}

	// questao6 //questao7
	public String buscarMenorRotaIniciandoEm_X_FinalizandoEm(Vertice vIni, Vertice vFim) {
		String caminho = "";
		if (vFim.getCaminho().contains(vIni)) {
			for (Vertice vert : vFim.getCaminho()) {
				caminho = caminho + vert.getNome();
			}
			caminho = caminho + vFim.getNome();
		}
		return caminho + " ";
	}

	public int menorDistanciaEntreAeXPassandoPorY(Vertice passandoPor, Vertice verticeFim) {
		return verticeFim.getCaminho().contains(passandoPor) ? verticeFim.getDistancia() : 0;
	}

	// questao1
	// questao2
	public int buscarMenorDistanciaNoGrafoIniciandoEm_X_FinalizandoEm(Vertice vInicio, Vertice vFim) {
		return vFim.getCaminho().contains(vInicio) == true ? vFim.getDistancia() : 0;
	}

	// questao3 /questao1
	public int buscarMenorDistanciaNoGrafoIniciandoEm_A_PassandoPor_e_FinalizandoEm(Vertice vInicio, Vertice passandoPor, Vertice verticeC) {

		buscarMenoCaminhoporUmDeterminadoVertice(vInicio, passandoPor);
		return buscarMenorDistanciaNoGrafoIniciandoEm_X_FinalizandoEm(vInicio, verticeC);
	}

	private void iniciarDistancias(Grafo grafo) {
		Set<Vertice> vertices = grafo.getVertices();
		for (Vertice vert : vertices) {
			vert.setDistancia(Integer.MAX_VALUE);
		}

	}

	private void calcularMenorDistancia(Vertice verticeVizinho, Integer valorDoPeso, Vertice verticeComMenorDistancia) {

		LinkedList<Vertice> listaDeCaminhos = null;
		int distancia = verticeComMenorDistancia.getDistancia();

		if (distancia + valorDoPeso < verticeVizinho.getDistancia()) {

			verticeVizinho.setDistancia(distancia + valorDoPeso);
			listaDeCaminhos = new LinkedList<>(verticeComMenorDistancia.getCaminho());

			listaDeCaminhos.add(verticeComMenorDistancia);
			verticeVizinho.setCaminho(listaDeCaminhos);
		}
	}

	private Vertice getVerticeMenorDistancia(Set<Vertice> verticesNaoVisitados) {
		int menorDistanciaDeTodos = Integer.MAX_VALUE;
		Vertice verticeComMenorDistancia = null;
		for (Vertice vert : verticesNaoVisitados) {
			if (vert.getDistancia() < menorDistanciaDeTodos) {
				menorDistanciaDeTodos = vert.getDistancia();
				verticeComMenorDistancia = vert;
			}
		}
		return verticeComMenorDistancia;
	}

	private void buscarMenoCaminhoporUmDeterminadoVertice(Vertice vInicio, Vertice passandoPor) {
		limparVerticeInicio(vInicio);

		Set<Vertice> verticesVisitados = new HashSet<>();
		Set<Vertice> verticesNaoVisitados = new HashSet<>();

		verticesNaoVisitados.add(vInicio);

		while (verticesNaoVisitados.size() > 0) {
			Vertice verticeComMenorDistancia = getVerticeMenorDistancia(verticesNaoVisitados);
			for (Entry<Vertice, Integer> visinho : verticeComMenorDistancia.getVerticesVisinhos().entrySet()) {

				Vertice verticeVizinho = visinho.getKey();
				Integer valorDoPeso = visinho.getValue();
				if (verticeVizinho.equals(passandoPor)) {
					if (!verticesVisitados.contains(verticeVizinho)) {
						calcularMenorDistancia(verticeVizinho, valorDoPeso, verticeComMenorDistancia);
						verticesNaoVisitados.add(verticeVizinho);
					}
				}
			}
			verticesVisitados.add(verticeComMenorDistancia);
			verticesNaoVisitados.remove(verticeComMenorDistancia);
		}
	}

	private static void limparVerticeInicio(Vertice vInicio) {
		vInicio.setDistancia(0);
		vInicio.setCaminho(Collections.emptyList());
	}

}
