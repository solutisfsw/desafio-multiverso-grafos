package com.solutis.desafio;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.solutis.desafio.model.Grafo;
import com.solutis.desafio.model.Vertice;
import com.solutis.desafio.service.MenorCaminho;

@SpringBootApplication
public class AlgoritimoDeGrafosSolutisApplication implements CommandLineRunner {

	@Autowired
	private Grafo grafo;
	@Autowired
	private Questoes questoes;

	public static void main(String[] args) {
		SpringApplication.run(AlgoritimoDeGrafosSolutisApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("Iniciando o Grafo...");

		Vertice verticeA, verticeB, verticeC, verticeD, verticeE;

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

		this.grafo = new Grafo();
		grafo.adicionarVertice(listadeVertices);

		questoes.questao1(verticeA, verticeB, verticeC, new MenorCaminho(this.grafo, verticeA));
		questoes.questao2(verticeA, verticeD, new MenorCaminho(this.grafo, verticeA));
		questoes.questao3(verticeA, verticeC, verticeD, new MenorCaminho(this.grafo, verticeA));
		questoes.questao4(grafo, verticeC);
		questoes.questao5(grafo, verticeC);

		questoes.questao6(grafo, verticeA, verticeC);
		questoes.questao7(grafo, verticeB);
		questoes.questao8(grafo, verticeC, verticeC, 300);
	}

}
