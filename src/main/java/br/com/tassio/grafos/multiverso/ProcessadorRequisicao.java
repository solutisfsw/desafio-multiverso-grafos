package br.com.tassio.grafos.multiverso;

import java.util.Collections;

import br.com.tassio.grafos.multiverso.pojo.Caminho;
import br.com.tassio.grafos.multiverso.pojo.Deslocamento;
import br.com.tassio.grafos.multiverso.pojo.RequisicaoCaminho;
import br.com.tassio.grafos.multiverso.pojo.RequisicaoCaminho.TipoCaminho;
import br.com.tassio.grafos.multiverso.pojo.Rota;
import br.com.tassio.grafos.multiverso.pojo.Solucao;
import br.com.tassio.grafos.multiverso.pojo.Vertice;

public class ProcessadorRequisicao {

	public Solucao processar(RequisicaoCaminho requisicao) {

		Solucao solucao = new Solucao(requisicao);

		seguirCaminho(requisicao, null, solucao);

		switch (requisicao.getTipoCaminho()) {

		case MENOR_POSSIVEL:
			if (solucao.getListaCaminho().size() > 0) {
				Collections.sort(solucao.getListaCaminho());
				Caminho menorCaminho = solucao.getListaCaminho().get(0);
				solucao.getListaCaminho().clear();
				solucao.getListaCaminho().add(menorCaminho);
			}
			break;

		default:
			break;
		}

		return solucao;

	}

	public void seguirCaminho(RequisicaoCaminho requisicao, Caminho caminho, Solucao solucao) {

		if (caminho == null) {
			caminho = new Caminho();
		}

		Vertice origem = null;
		Deslocamento deslocamento = null;

		if (caminho.getListaDeslocamento().size() == 0) {
			origem = requisicao.getOrigem();
		} else {
			deslocamento = caminho.getListaDeslocamento().get(caminho.getListaDeslocamento().size() - 1);
			origem = deslocamento.getDestino();
		}

		// Se o vértice possuir apenas uma Rota possível não precisa bifurcar os caminhos
		if (origem.getListaRota().size() == 1) {

			Rota rota = origem.getListaRota().iterator().next();

			criarDeslocamentoVerificarCriterioParada(origem, rota, caminho, requisicao, solucao);

		} else {

			// Se o vértice possuir mais de uma rota possível criar uma ramificação do caminho para cada possibilidade
			for (Rota rota : origem.getListaRota()) {

				Caminho novoCaminho = (Caminho) caminho.clone();

				criarDeslocamentoVerificarCriterioParada(origem, rota, novoCaminho, requisicao, solucao);

			}

		}

	}

	private void criarDeslocamentoVerificarCriterioParada(Vertice origem, Rota rota, Caminho caminho, RequisicaoCaminho requisicao, Solucao solucao) {

		// Se a requisição aceita qualquer caminho e já foi encontrada uma solução, interromper a pesquisa
		if (TipoCaminho.QUALQUER.equals(requisicao.getTipoCaminho()) && solucao.getListaCaminho().size() > 0) {
			return;
		}
		
		Vertice destino = Start.grafo.get(rota.getNomeVertice());

		caminho.adicionarDeslocamento(new Deslocamento(origem.getNome(), destino.getNome(), rota.getDistancia()));

		verificarCriterioParada(requisicao, caminho, destino, solucao);

	}

	private void verificarCriterioParada(RequisicaoCaminho requisicao, Caminho caminho, Vertice destino, Solucao solucao) {

		// Verificar se o destino foi alcançado
		if (destino.getNome().equalsIgnoreCase(requisicao.getDestino().getNome())) {

			if (requisicao.getQuantidadeMaximaParadaPorCaminho() > 0 && (caminho.getListaDeslocamento().size() - 1) > requisicao.getQuantidadeMaximaParadaPorCaminho()) {

				// Se a requisição possui quantidade máxima de parada e ela foi ultrapassada, o caminho não será aceito como solução
				return;

			} else if (requisicao.getDistanciaMaximaPorCaminho() > 0 && caminho.getDistanciaTotal() > requisicao.getDistanciaMaximaPorCaminho()) {

				// Se a requisição possui distância máxima e ela foi ultrapassada, o caminho não será aceito como solução
				return;

			} else if (requisicao.getListaVerticeObrigatorio() != null && requisicao.getListaVerticeObrigatorio().size() > 0) {

				// Se a requisição possui vértices obrigatórios e todos não foram atendidos o caminho não será aceito
				boolean satisfeito = false;
				for (Vertice verticeObrigatorio : requisicao.getListaVerticeObrigatorio()) {

					satisfeito = false;
					for (Deslocamento deslocamento : caminho.getListaDeslocamento()) {
						if (verticeObrigatorio.getNome().equalsIgnoreCase(deslocamento.getOrigem().getNome()) || verticeObrigatorio.getNome().equalsIgnoreCase(deslocamento.getDestino().getNome())) {
							satisfeito = true;
							break;
						}
					}

					if (!satisfeito) {
						return;
					}

				}

				solucao.getListaCaminho().add(caminho);
				return;

			} else {
				solucao.getListaCaminho().add(caminho);
				return;
			}

		}

		// Se o destino não foi alcançado, porém a requisição possui limite de distância ou paradas o caminho será interrompido e não será aceito como solução
		if (requisicao.getDistanciaMaximaPorCaminho() > 0 && caminho.getDistanciaTotal() > requisicao.getDistanciaMaximaPorCaminho()) {
			return;
		}

		if (requisicao.getQuantidadeMaximaParadaPorCaminho() > 0 && (caminho.getListaDeslocamento().size() - 1) > requisicao.getQuantidadeMaximaParadaPorCaminho()) {
			return;
		}

		// Se o próximo destino já consta no caminho significa que ele está retornando ao mesmo ponto e o caminho deve ser descartado
		for (Deslocamento deslocamento : caminho.getListaDeslocamento()) {
			if (destino.getNome().equalsIgnoreCase(deslocamento.getOrigem().getNome())) {
				return;
			}
		}

		// Se nenhuma condição de parada foi satisfeita, seguir com as chamadas recursivas
		seguirCaminho(requisicao, caminho, solucao);

	}

}
