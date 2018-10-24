package com.adailton.multiverso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.adailton.multiverso.entity.Aresta;
import com.adailton.multiverso.entity.CalculosMultiverso;
import com.adailton.multiverso.entity.Caminho;
import com.adailton.multiverso.entity.UniversoVertice;

@SpringBootApplication
public class MultiversoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultiversoApplication.class, args);
		
		UniversoVertice a = new UniversoVertice("a");
		UniversoVertice b = new UniversoVertice("b");
		UniversoVertice c = new UniversoVertice("c");
		UniversoVertice d = new UniversoVertice("d");
		UniversoVertice e = new UniversoVertice("e");
		
		//adicionando as Arestas para cada classe
		a.setRotas(Arrays.asList(new Aresta(70, e),
								new Aresta(50, b),
								new Aresta(50, d) ));
		
		b.setRotas(Arrays.asList(new Aresta(40, c)));
		
		c.setRotas(Arrays.asList(new Aresta(40, d),
								new Aresta(20, e)));
		
		d.setRotas(Arrays.asList(new Aresta(40, c),
								new Aresta(80, e)));
		
		e.setRotas(Arrays.asList(new Aresta(30, b)));
		
		CalculosMultiverso calculo = new CalculosMultiverso();
		List<Caminho> cam = new ArrayList<>();
		
		calculo.calculaCaminhoPrincipal(c, c, cam);//entradas de testes
		
		System.out.println("total de caminhos possiveis: " + cam.size());
		System.out.println("\n");
		for(int i=0; i<cam.size();i++) {
			for(i=0; i<cam.size();i++) {
				System.out.println("qtd de paradas: "+ cam.get(i).getQtdParada());
				System.out.println("qtd total do percurso: " + cam.get(i).getTotalEspacoTempo());
				System.out.println(cam.get(i).getLetrasDoCaminho());
				System.out.println("*************************");
			}
		}
	}
}
