package com.adailton.multiverso.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.omg.CORBA.PUBLIC_MEMBER;
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

			
	@GetMapping("/")
	public String home() {		
		return "view/multiversoView";
	}
	
	@PostMapping("/listaCaminho")		
	public ModelAndView listarCaminhos() {
		ListaDeVertice lista = new ListaDeVertice();
		lista.iniciaVertices();
		lista.iniciaListaVertice();
		String letra1="letra1",letra2="letra2";
		UniversoVertice origem = lista.convertStringVertice(letra1);
		UniversoVertice destino = lista.convertStringVertice(letra2);
		CalculosMultiverso calculo = new CalculosMultiverso();
		List<Caminho> cam = new ArrayList<>();//criando lista para empilhar os caminhos
		calculo.calculaCaminhoPrincipal(origem, destino, cam);
		ModelAndView retorno = new ModelAndView("view/multiversoView");
		retorno.addObject("listaCaminhos",cam);//dando nome ao objeto e metodo de trazer todos 
		return retorno;
	}
	
	//@PostMapping("/entraParametro"){
	
		
	//}
	
	/*@PostMapping//para poder inserir os dados no BD
	public ModelAndView cadastrarPessoa(Pessoa pessoa) {
		repository.save(pessoa);
		return new ModelAndView("redirect:/listaPessoa");
	}*/
}
