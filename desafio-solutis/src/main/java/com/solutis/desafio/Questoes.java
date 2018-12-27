package com.solutis.desafio;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

import com.solutis.desafio.model.Grafo;
import com.solutis.desafio.model.Vertice;
import com.solutis.desafio.service.CalcularDistancias;
import com.solutis.desafio.service.MenorCaminho;

@Service
public class Questoes {

	public void questao8(Grafo grafo, Vertice vInicio, Vertice vFim, int maxPesos) {
		System.out.println("8- O número de diferentes rotas saindo de C e voltando a C com distância máxima de 300 unidades de espaço-tempo, é");
		CalcularDistancias calcular = new CalcularDistancias(grafo);
		Map<List<Vertice>, Integer> rotas = calcular.getRotasEntreDoisVerticesComMaximoXPesos(vInicio, vFim, 300);

		System.out.println(rotas.size());
		System.out.println("sao elas :");

		for (Entry<List<Vertice>, Integer> vertices : rotas.entrySet()) {
			System.out.print(vInicio.getNome() + "-> ");
			List<Vertice> nos = vertices.getKey();
			nos.forEach(item -> System.out.print(item.getNome()));

			System.out.println(" ");
		}
		System.out.println(" ");
	}

	public void questao6(Grafo grafo, Vertice verticeA, Vertice verticeC) {
		System.out.println("6- A menor rota (em espaço-tempo) entre A e C, é:");
		MenorCaminho menorCaminho = new MenorCaminho(grafo, verticeA);
		System.out.println(menorCaminho.buscarMenorRotaIniciandoEm_X_FinalizandoEm(verticeA, verticeC));
		System.out.println(" ");
	}

	public void questao7(Grafo grafo, Vertice verticeB) {
		System.out.println("7- A menor rota (em espaço-tempo) saindo de B e voltando a B, é:");

		MenorCaminho menorCaminho = new MenorCaminho(grafo, verticeB);
		String rota = menorCaminho.buscarMenorRotaIniciandoEm_X_FinalizandoEm(verticeB, verticeB);
		System.out.println(rota);
		System.out.println(" ");
	}

	public void questao5(Grafo grafo, Vertice verticeC) {
		System.out.println("5- Número de rotas saindo de C e voltando a C com no máximo 4 paradas, é: ");
		CalcularDistancias calcular = new CalcularDistancias(grafo);
		List<List<Vertice>> numeroDeRotasSaindoDe_X_eChegandoEm_Y = calcular.getRotasEntreDoisVerticesComXParadas(verticeC, verticeC, 4);

		if (numeroDeRotasSaindoDe_X_eChegandoEm_Y.size() == 0) {
			System.out.println("não existe rota!");
		} else {
			System.out.print(numeroDeRotasSaindoDe_X_eChegandoEm_Y.size());
		}
		System.out.println(" ");

		System.out.println("sao elas :");
		System.out.println(" ");
		for (List<Vertice> vertices : numeroDeRotasSaindoDe_X_eChegandoEm_Y) {
			System.out.print(verticeC.getNome() + "-> ");
			vertices.forEach(item -> System.out.print(item.getNome()));
			System.out.println(" ");
		}
		System.out.println(" ");
	}

	public void questao4(Grafo grafo, Vertice verticeC) {
		System.out.println("4- Número de rotas saindo de C e voltando a C com no máximo 3 paradas, é: ");
		CalcularDistancias calcular = new CalcularDistancias(grafo);
		List<List<Vertice>> numeroDeRotasSaindoDe_X_eChegandoEm_Y = calcular.getRotasEntreDoisVerticesComXParadas(verticeC, verticeC, 3);

		if (numeroDeRotasSaindoDe_X_eChegandoEm_Y.size() == 0) {
			System.out.println("não existe rota!");
		} else {
			System.out.print(numeroDeRotasSaindoDe_X_eChegandoEm_Y.size());
		}
		System.out.println(" ");

		System.out.println("sao elas :");
		System.out.println(" ");

		for (List<Vertice> vertices : numeroDeRotasSaindoDe_X_eChegandoEm_Y) {
			System.out.print(verticeC.getNome() + "-> ");
			vertices.forEach(item -> System.out.print(item.getNome()));
			System.out.println(" ");
		}
		System.out.println(" ");
	}

	public void questao3(Vertice verticeA, Vertice verticeC, Vertice verticeD, MenorCaminho menorCaminho) {
		System.out.println("3- A distância de A a C passando por D?, é: ");

		int distacia = menorCaminho.buscarMenorDistanciaNoGrafoIniciandoEm_A_PassandoPor_e_FinalizandoEm(verticeA, verticeD, verticeC);

		if (distacia == 0) {
			System.out.println("não existe rota!");
		} else {
			System.out.print(distacia);
		}
		System.out.println(" ");
	}

	public void questao2(Vertice verticeA, Vertice verticeD, MenorCaminho menorCaminho) {
		System.out.println("2- A distância entre A e D, é: ");

		int distanciaEntre_A_E_X = menorCaminho.buscarMenorDistanciaNoGrafoIniciandoEm_X_FinalizandoEm(verticeA, verticeD);

		if (distanciaEntre_A_E_X == 0) {
			System.out.println("não existe rota!");
		} else {
			System.out.print(distanciaEntre_A_E_X);
		}

		System.out.println(" ");
	}

	public void questao1(Vertice verticeA, Vertice verticeB, Vertice verticeC, MenorCaminho menorCaminho) {
		System.out.println("1- A Menor distância do grafo Iniciando em A, Passando Por B e Finalizando em C, é: ");

		int menorDistInicEm_A_PassPorCeFinB = menorCaminho.buscarMenorDistanciaNoGrafoIniciandoEm_A_PassandoPor_e_FinalizandoEm(verticeA, verticeB,
				verticeC);
		if (menorDistInicEm_A_PassPorCeFinB == 0) {
			System.out.println("não existe rota!");
		} else {
			System.out.print(menorDistInicEm_A_PassPorCeFinB);
		}
		System.out.println(" ");
		System.out.println(" ");
	}

}
