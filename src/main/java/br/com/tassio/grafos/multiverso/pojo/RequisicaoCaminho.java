package br.com.tassio.grafos.multiverso.pojo;

import java.util.ArrayList;
import java.util.List;

import br.com.tassio.grafos.multiverso.Start;

public class RequisicaoCaminho {

	private String origem;
	private String destino;
	private List<String> listaVerticeObrigatorio = new ArrayList<String>();
	private int quantidadeMaximaParadaPorCaminho = -1;
	private int distanciaMaximaPorCaminho = -1;
	private TipoCaminho tipoCaminho = TipoCaminho.MENOR_POSSIVEL;

	public RequisicaoCaminho(String origem, String destino, List<String> listaVerticeObrigatorio, int quantidadeMaximaParadaPorCaminho, int distanciaMaximaPorCaminho, TipoCaminho tipoCaminho) {

		this.origem = origem;
		this.destino = destino;

		if (listaVerticeObrigatorio != null) {
			this.listaVerticeObrigatorio = listaVerticeObrigatorio;
		}

		this.quantidadeMaximaParadaPorCaminho = quantidadeMaximaParadaPorCaminho >= 0 ? quantidadeMaximaParadaPorCaminho : -1;

		this.distanciaMaximaPorCaminho = distanciaMaximaPorCaminho > 0 ? distanciaMaximaPorCaminho : -1;

		this.tipoCaminho = (tipoCaminho != null) ? tipoCaminho : TipoCaminho.MENOR_POSSIVEL;

	}

	public Vertice getOrigem() {
		return Start.grafo.get(origem);
	}

	public Vertice getDestino() {
		return Start.grafo.get(destino);
	}

	public List<Vertice> getListaVerticeObrigatorio() {

		List<Vertice> listaVertice = new ArrayList<Vertice>();
		for (String v : listaVerticeObrigatorio) {
			listaVertice.add(Start.grafo.get(v));
		}

		return listaVertice;
	}

	public int getQuantidadeMaximaParadaPorCaminho() {
		return quantidadeMaximaParadaPorCaminho;
	}

	public int getDistanciaMaximaPorCaminho() {
		return distanciaMaximaPorCaminho;
	}

	public TipoCaminho getTipoCaminho() {
		return tipoCaminho;
	}

	@Override
	public String toString() {
		return origem + "->" + destino + " passando por: " + listaVerticeObrigatorio + " Max. Paradas: " + quantidadeMaximaParadaPorCaminho + " Max. Distancia: " + distanciaMaximaPorCaminho + " tipo caminho: " + tipoCaminho;
	}

	public static enum TipoCaminho {
		MENOR_POSSIVEL, QUALQUER, TODOS;
	}

}
