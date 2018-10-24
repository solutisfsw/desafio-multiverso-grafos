package com.adailton.multiverso.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.adailton.multiverso.entity.Aresta;
import com.adailton.multiverso.entity.CalculosMultiverso;
import com.adailton.multiverso.entity.Caminho;
import com.adailton.multiverso.entity.UniversoVertice;

@Controller
public class MultiversoController {

	UniversoVertice a = new UniversoVertice("a");
	UniversoVertice b = new UniversoVertice("b");
	UniversoVertice c = new UniversoVertice("c");
	UniversoVertice d = new UniversoVertice("d");
	UniversoVertice e = new UniversoVertice("e");
	
	public void adicionarArestas() {
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
	}
	
	CalculosMultiverso calculo = new CalculosMultiverso();
	List<Caminho> cam = new ArrayList<>();
	
			
	@GetMapping("/")
	public String home() {
		
		return "view/multiversoView";
	}
	
	//@PostMapping("/defineParametro"){
		
		
	//}
}
