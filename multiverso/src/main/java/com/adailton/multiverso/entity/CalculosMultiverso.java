package com.adailton.multiverso.entity;

import java.util.ArrayList;
import java.util.List;

public class CalculosMultiverso {
	private List<Caminho> listaCaminho = new ArrayList<>();
	
	
	
	
	public void calculaCaminho(UniversoVertice verticeOrigem, UniversoVertice verticeDestino,List<Caminho> listaCaminho) {
		
		
		//for(int i= 0; i<5; i++) {
			for(Aresta v:verticeOrigem.getRotas()) {
				Caminho cam = new Caminho();
				if(v.getDestino().getNome().equals(verticeDestino.getNome())) {//comparando se tem uma aresta do mesmo nome do destino
					cam.somaTotalEP(v.getespaco_tempo());
					cam.setletrasDoCaminho(v.getDestino().getNome());
					cam.setQtdParada(cam.getLetrasDoCaminho().size());
					listaCaminho.add(cam);
				}else {
					cam.somaTotalEP(v.getespaco_tempo());
					cam.setletrasDoCaminho(v.getDestino().getNome());
					UniversoVertice verticeAuxiliar = v.getDestino();
					//calculaCaminho(verticeAuxiliar, verticeDestino, listaCaminho);
					
					for(Aresta v2:verticeAuxiliar.getRotas()) {
						if(v2.getDestino().getNome().equals(verticeDestino.getNome())) {//comparando se tem uma aresta do mesmo nome do destino
							cam.somaTotalEP(v2.getespaco_tempo());
							cam.setletrasDoCaminho(v2.getDestino().getNome());
							cam.setQtdParada(cam.getLetrasDoCaminho().size());
							listaCaminho.add(cam);
						}else {
							cam.somaTotalEP(v2.getespaco_tempo());
							cam.setletrasDoCaminho(v2.getDestino().getNome());
							UniversoVertice verticeAuxiliar2 = v2.getDestino();
							//calculaCaminho(verticeAuxiliar, verticeDestino, listaCaminho);
							
							for(Aresta v3:verticeAuxiliar2.getRotas()) {
								if(v3.getDestino().getNome().equals(verticeDestino.getNome())) {//comparando se tem uma aresta do mesmo nome do destino
									cam.somaTotalEP(v3.getespaco_tempo());
									cam.setletrasDoCaminho(v3.getDestino().getNome());
									cam.setQtdParada(cam.getLetrasDoCaminho().size());
									listaCaminho.add(cam);
								}
							}
						}
					}
				}	
			}		
		}
	}

