package com.adailton.multiverso.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import com.adailton.multiverso.entity.Aresta;
import com.adailton.multiverso.entity.CalculosMultiverso;
import com.adailton.multiverso.entity.Caminho;
import com.adailton.multiverso.entity.ListaDeVertice;
import com.adailton.multiverso.entity.UniversoVertice;

@Controller
public class MultiversoController {
	
	ListaDeVertice lista = new ListaDeVertice();	
	CalculosMultiverso calculo = new CalculosMultiverso();
	UniversoVertice verticeOrigem = new UniversoVertice();
	UniversoVertice verticeDestino = new UniversoVertice();
	List<Caminho> listaCaminho = new ArrayList<>();
	
	@GetMapping("/")
	public String home() {		
		return "view/multiversoView";
	}
	
	@PostMapping		
	public ModelAndView calucularCaminhos(String origem, String destino) {
		listaCaminho.removeAll(listaCaminho);
		
		lista.iniciaVertices();
		lista.iniciaListaVertice();		
		verticeOrigem = lista.convertStringVertice(origem);
		verticeDestino = lista.convertStringVertice(destino);
		calculo.calculaCaminhoPrincipal(verticeOrigem, verticeDestino, listaCaminho);
		calculo.menorCaminho(listaCaminho);
		
		ModelAndView retorno = new ModelAndView("view/multiversoView");
		retorno.addObject("listaCaminhos",listaCaminho);//dando nome ao objeto e metodo de trazer todos 
		return retorno;
	}
	
}
