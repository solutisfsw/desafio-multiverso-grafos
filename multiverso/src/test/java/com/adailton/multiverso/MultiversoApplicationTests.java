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
	
	
	
	@Before
	public void SetupContext() {		
		lista.iniciaVertices();
		lista.iniciaListaVertice();
		
		
	}
	@Test
	public void verificaDeA_B() {

		List<Caminho> listaCaminho = new ArrayList<>();		
		calculo.calculaCaminhoPrincipal(lista.getA(), lista.getB(), listaCaminho);	
		calculo.menorCaminho(listaCaminho);
		
		assertEquals(50,listaCaminho.get(0).getTotalEspacoTempo());
		assertEquals(1, listaCaminho.get(0).getQtdParada());	
		assertEquals("VIAGEM RÁPIDA",listaCaminho.get(0).getMenorCaminho());
		
	}
	
	@Test
	public void verficaDeA_C() {
		
		List<Caminho> listaCaminho = new ArrayList<>();
		calculo.calculaCaminhoPrincipal(lista.getA(), lista.getC(), listaCaminho);
		calculo.menorCaminho(listaCaminho);
		
		assertEquals(140,listaCaminho.get(0).getTotalEspacoTempo());
		assertEquals(3, listaCaminho.get(0).getQtdParada());
		
		assertEquals(90,listaCaminho.get(1).getTotalEspacoTempo());
		assertEquals(2, listaCaminho.get(1).getQtdParada());			
		
	}
	
	@Test
	public void verficaDeA_D() {
		
		List<Caminho> listaCaminho = new ArrayList<>();
		calculo.calculaCaminhoPrincipal(lista.getA(), lista.getD(), listaCaminho);
		calculo.menorCaminho(listaCaminho);
		
		assertEquals(180,listaCaminho.get(0).getTotalEspacoTempo());
		assertEquals(4, listaCaminho.get(0).getQtdParada());
		
		assertEquals(50,listaCaminho.get(2).getTotalEspacoTempo());
		assertEquals(1, listaCaminho.get(2).getQtdParada());
		assertEquals("VIAGEM RÁPIDA",listaCaminho.get(2).getMenorCaminho());
		
		assertEquals(130,listaCaminho.get(1).getTotalEspacoTempo());
		assertEquals(3, listaCaminho.get(1).getQtdParada());
		assertEquals("",listaCaminho.get(1).getMenorCaminho());		
		
	}

}
