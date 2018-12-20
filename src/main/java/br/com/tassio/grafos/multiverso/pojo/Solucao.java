package br.com.tassio.grafos.multiverso.pojo;

import java.util.ArrayList;
import java.util.List;

public class Solucao {

	private RequisicaoCaminho requisicao;
	private List<Caminho> listaCaminho = new ArrayList<Caminho>();

	public Solucao(RequisicaoCaminho requisicao) {
		this.requisicao = requisicao;
	}

	public List<Caminho> getListaCaminho() {
		return listaCaminho;
	}

	public RequisicaoCaminho getRequisicao() {
		return requisicao;
	}

}
