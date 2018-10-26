package com.adailton.multiverso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.adailton.multiverso.entity.Aresta;
import com.adailton.multiverso.entity.CalculosMultiverso;
import com.adailton.multiverso.entity.Caminho;
import com.adailton.multiverso.entity.ListaDeVertice;
import com.adailton.multiverso.entity.UniversoVertice;

@SpringBootApplication
public class MultiversoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultiversoApplication.class, args);
		

		ListaDeVertice lista = new ListaDeVertice();
		lista.iniciaVertices();
		lista.iniciaListaVertice();
		
		String b1= "b";
		String b2= "b";
		
		UniversoVertice origem = lista.convertStringVertice(b1);
		UniversoVertice destino = lista.convertStringVertice(b2);
		
		CalculosMultiverso calculo = new CalculosMultiverso();
		List<Caminho> cam = new ArrayList<>();//criando lista para empilhar os caminhos
		
		calculo.calculaCaminhoPrincipal(origem, destino, cam);//entradas de testes
		calculo.menorCaminho(cam);
		
		System.out.println("total de caminhos possiveis: " + cam.size());
		System.out.println("\n");
		for(int i=0; i<cam.size();i++) {
			for(i=0; i<cam.size();i++) {
				System.out.println("qtd de paradas: "+ cam.get(i).getQtdParada());
				System.out.println("qtd total do percurso: " + cam.get(i).getTotalEspacoTempo());
				System.out.println(cam.get(i).getLetrasDoCaminho());
				System.out.println(cam.get(i).getMenorCaminho());
				System.out.println("*************************");
			}
		}
	}
}
