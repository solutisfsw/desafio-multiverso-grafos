package com.adailton.multiverso;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.adailton.multiverso.entity.CalculosMultiverso;
import com.adailton.multiverso.entity.Caminho;
import com.adailton.multiverso.entity.ListaDeVertice;
import com.adailton.multiverso.entity.UniversoVertice;
import com.fasterxml.jackson.databind.Module.SetupContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MultiversoApplicationTests {

	ListaDeVertice lista = new ListaDeVertice();	
	CalculosMultiverso calculo = new CalculosMultiverso();
	UniversoVertice verticeOrigem = new UniversoVertice();
	UniversoVertice verticeDestino = new UniversoVertice();
	List<Caminho> listaCaminho = new ArrayList<>();
	
	
	@Before
	public void SetupContext() {		
		lista.iniciaVertices();
		lista.iniciaListaVertice();
		calculo.calculaCaminhoPrincipal(lista.getA(), lista.getB(), listaCaminho);
		calculo.menorCaminho(listaCaminho);
	}
	@Test
	public void verificaDeA_B() {			
		assertEquals("b",listaCaminho.get(0).getLetrasDoCaminho().get(0).equals(listaCaminho));
		//assertEquals(50,listaCaminho.get(0).getTotalEspacoTempo());
		//assertEquals(1,listaCaminho.get(0).getQtdParada());
	}
	
	@Test
	public void verficaDeA_C() {
		calculo.calculaCaminhoPrincipal(lista.getA(), lista.getC(), listaCaminho);
		calculo.menorCaminho(listaCaminho);
	
		assertEquals("VIAGEM RÁPIDA",listaCaminho.get(0).getMenorCaminho());//menor caminho
		assertEquals(listaCaminho.get(0).getLetrasDoCaminho().get(0),"e,b,c");//resultado do priemiro indice
		assertEquals(listaCaminho.get(2).getLetrasDoCaminho().get(2),"0");
		
		
	}

}
