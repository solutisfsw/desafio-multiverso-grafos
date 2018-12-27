package com.solutis.desafio;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.solutis.desafio.model.Grafo;
import com.solutis.desafio.model.Vertice;
import com.solutis.desafio.service.CalcularDistancias;
import com.solutis.desafio.service.MenorCaminho;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlgoritimoDeGrafosSolutisApplicationTests {

	private Grafo grafo;
	private Vertice verticeA, verticeB, verticeC, verticeD, verticeE;

	@Before
	public void init() {
		verticeA = new Vertice("A");
		verticeB = new Vertice("B");
		verticeC = new Vertice("C");
		verticeD = new Vertice("D");
		verticeE = new Vertice("E");

		verticeA.adcionarDistanciaAosVisinhos(verticeB, 50);
		verticeA.adcionarDistanciaAosVisinhos(verticeD, 50);
		verticeA.adcionarDistanciaAosVisinhos(verticeE, 70);

		verticeB.adcionarDistanciaAosVisinhos(verticeC, 40);

		verticeC.adcionarDistanciaAosVisinhos(verticeE, 20);
		verticeC.adcionarDistanciaAosVisinhos(verticeD, 40);

		verticeD.adcionarDistanciaAosVisinhos(verticeE, 80);
		verticeD.adcionarDistanciaAosVisinhos(verticeC, 40);

		verticeE.adcionarDistanciaAosVisinhos(verticeB, 30);

		Set<Vertice> listadeVertices = new HashSet<>();
		listadeVertices.add(verticeA);
		listadeVertices.add(verticeB);
		listadeVertices.add(verticeC);
		listadeVertices.add(verticeD);
		listadeVertices.add(verticeE);

		grafo = new Grafo();
		grafo.adicionarVertice(new HashSet<>(listadeVertices));
	}

	// A menor distância de A a C passando por B?
	@Test
	public void testBuscarDistanciaEntreA_e_C_PassandoPor_B() {
		MenorCaminho dijkstra = new MenorCaminho(grafo, verticeA);
		assertEquals(90, dijkstra.menorDistanciaEntreAeXPassandoPorY(verticeB, verticeC));
	}

	// A distância entre A e D?
	@Test
	public void testBuscarDistanciaEntreA_e_D() {
		MenorCaminho dijkstra = new MenorCaminho(grafo, verticeA);
		assertEquals(50, dijkstra.buscarMenorDistanciaNoGrafoIniciandoEm_X_FinalizandoEm(verticeA, verticeD));
	}

	// A distância de A a C passando por D?
	@Test
	public void testBuscarDistanciaEntreA_e_C_PassandoPor_D() {
		MenorCaminho dijkstra = new MenorCaminho(grafo, verticeA);
		assertEquals(90, dijkstra.buscarMenorDistanciaNoGrafoIniciandoEm_A_PassandoPor_e_FinalizandoEm(verticeA,
				verticeD, verticeC));
	}

	// A menor rota (em espaço-tempo) entre A e C?
	@Test
	public void testMenorRotaIniciandoEmA_FinalizandoEmC() {
		MenorCaminho dijkstra = new MenorCaminho(grafo, verticeA);
		assertEquals("ABC ", dijkstra.buscarMenorRotaIniciandoEm_X_FinalizandoEm(verticeA, verticeC));
	}

	// A menor rota (em espaço-tempo) saindo de B e voltando a B?
	@Test
	public void testMenorRotaSaindoDe_B_eChegandoEm_B() {
		MenorCaminho dijkstra = new MenorCaminho(grafo, verticeB);
		assertEquals("BCEB ", dijkstra.buscarMenorRotaIniciandoEm_X_FinalizandoEm(verticeB,verticeB));
		//assertEquals("BCEB ", dijkstra.buscarMenorRotaIniciandoEm_B_Finalizando_B(verticeB));
	}

	// O número de rotas saindo de C e voltando a C com no máximo 3 paradas?
	@Test
	public void numeroDeRotasSaindoDe_C_e_VoltandoParaC_comNoMaximo3Paradas() {
		CalcularDistancias calcular = new CalcularDistancias(grafo);
		assertEquals(2, calcular.getRotasEntreDoisVerticesComXParadas(verticeC, verticeC, 3).size());
	}

	@Test
	public void numeroDeRotasEntre_A_e_C_comNoMaximo4Paradas() {
		CalcularDistancias calcular = new CalcularDistancias(grafo);
		assertEquals(4, calcular.getRotasEntreDoisVerticesComXParadas(verticeA, verticeC, 4).size());
	}

	// O número de diferentes rotas saindo de C e voltando a C com distância máxima de 300 unidades de espaço-tempo?
	@Test
	public void testNumeroDeDiferentesRotasSaindoDe_C_e_VoltandoPara_C_ComDistânciaMaxima_De300Unidades() {
		CalcularDistancias calcular = new CalcularDistancias(grafo);
		Map<List<Vertice>, Integer> map = calcular.getRotasEntreDoisVerticesComMaximoXPesos(verticeC, verticeC, 300);
		assertEquals(3, map.size());
	}
	
	@Test
	public void testNumeroDeDiferentesRotasSaindoDe_C_e_VoltandoPara_C_ComDistânciaMaxima_De100Unidades() {
		CalcularDistancias calcular = new CalcularDistancias(grafo);
		Map<List<Vertice>, Integer> map = calcular.getRotasEntreDoisVerticesComMaximoXPesos(verticeC, verticeC, 100);
		assertEquals(2, map.size());
	}
}
