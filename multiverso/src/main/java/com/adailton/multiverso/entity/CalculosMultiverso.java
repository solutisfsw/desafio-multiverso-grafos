package com.adailton.multiverso.entity;

import java.util.List;

public class CalculosMultiverso {

	private Caminho caminho1 = new Caminho();
	
	
	
	public Caminho calculaDistancia(UniversoVertice verticeOrigem, UniversoVertice verticeDestino) {
		
		Caminho cam = new Caminho();
		//for(int i= 0; i<5; i++) {
			for(Aresta v:verticeOrigem.getRotas()) {
				if(v.getDestino().getNome().equals(verticeDestino.getNome())) {//comparando se tem uma aresta do mesmo nome do destino
					cam.somaTotalEP(v.getespaco_tempo());
					cam.setCaminhoValido(true);
					cam.setletrasDoCaminho(v.getDestino().getNome());
				/*}else {*/
					
				}
			}
		//}
		return cam;
	}

}
