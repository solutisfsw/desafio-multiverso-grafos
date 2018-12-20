package br.com.tassio.grafo.multiverso.pojo;

import java.util.ArrayList;
import java.util.List;

public class Solucao {

	private RequisicaoCaminho requisicao;
	private List<Caminho> caminhos = new ArrayList<Caminho>();

	public Solucao(RequisicaoCaminho requisicao) {
		this.requisicao = requisicao;
	}

	public List<Caminho> getCaminhos() {
		return caminhos;
	}

	public RequisicaoCaminho getRequisicao() {
		return requisicao;
	}

}
