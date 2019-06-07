package com.adailton.multiverso.entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.management.Notification;

import org.hibernate.validator.internal.engine.messageinterpolation.parser.EscapedState;
import org.springframework.util.AlternativeJdkIdGenerator;

public class CalculosMultiverso {
	private List<Caminho> listaCaminho = new ArrayList<>();
	
	public void calculaCaminhoPrincipal(UniversoVertice verticeOrigem, UniversoVertice verticeDestino,List<Caminho> listaCaminho) {
		try {
			if(verticeDestino.getNome().equalsIgnoreCase("b")) {
				calculaCaminhoBB(verticeOrigem, verticeDestino, listaCaminho);
			}else if(verticeDestino.getNome().equalsIgnoreCase("e")) {
				calculaCaminhoEE(verticeOrigem, verticeDestino, listaCaminho);
			}
			else {
				calculaCaminhoNormal(verticeOrigem, verticeDestino, listaCaminho);
			}
		} catch (Exception e) {
			
			Caminho essecao = new Caminho();
			essecao.setletrasDoCaminho("Roteiro Invalido");
			listaCaminho.add(essecao);
		}
		
	}
	
	
	public void calculaCaminhoNormal(UniversoVertice verticeOrigem, UniversoVertice verticeDestino,List<Caminho> listaCaminho) {			
		
			for(Aresta v:verticeOrigem.getRotas()) {
				Caminho cam = new Caminho() ;//por conta disso não consegui fazer recursividade	
				if(!cam.isCaminhoValido()) {
					if(v.getDestino().getNome().equalsIgnoreCase(verticeDestino.getNome())) {
						cam.setCaminhoValido(true);
						cam.somaTotalEP(v.getespaco_tempo());
						cam.setletrasDoCaminho(v.getDestino().getNome());
						cam.setQtdParada(cam.getLetrasDoCaminho().size());
						listaCaminho.add(cam);	
						
					}else {
						cam.somaTotalEP(v.getespaco_tempo());
						cam.setletrasDoCaminho(v.getDestino().getNome());
						UniversoVertice verticeAuxiliar = v.getDestino();
						
						for(Aresta v2:verticeAuxiliar.getRotas()) {//segundo nó
							if(!cam.isCaminhoValido()) {
								if(v2.getDestino().getNome().equalsIgnoreCase(verticeDestino.getNome())) {
									cam.setCaminhoValido(true);
									cam.somaTotalEP(v2.getespaco_tempo());
									cam.setletrasDoCaminho(v2.getDestino().getNome());
									cam.setQtdParada(cam.getLetrasDoCaminho().size());
									listaCaminho.add(cam);
									
								}else {
									cam.somaTotalEP(v2.getespaco_tempo());
									cam.setletrasDoCaminho(v2.getDestino().getNome());
									UniversoVertice verticeAuxiliar3 = v2.getDestino();
									
									for(Aresta v3:verticeAuxiliar3.getRotas()) {//terceiro nó
										if(!cam.isCaminhoValido()) {
											if(v3.getDestino().getNome().equalsIgnoreCase(verticeDestino.getNome())) {
												cam.setCaminhoValido(true);
												cam.somaTotalEP(v3.getespaco_tempo());
												cam.setletrasDoCaminho(v3.getDestino().getNome());
												cam.setQtdParada(cam.getLetrasDoCaminho().size());
												listaCaminho.add(cam);	
												
											}else {
												cam.somaTotalEP(v3.getespaco_tempo());
												cam.setletrasDoCaminho(v3.getDestino().getNome());
												UniversoVertice verticeAuxiliar4 = v3.getDestino();
												
												for(Aresta v4:verticeAuxiliar4.getRotas()) {//quarto nó
													if(!cam.isCaminhoValido()) {
														if(v4.getDestino().getNome().equalsIgnoreCase(verticeDestino.getNome())) {
															cam.setCaminhoValido(true);
															cam.somaTotalEP(v4.getespaco_tempo());
															cam.setletrasDoCaminho(v4.getDestino().getNome());
															cam.setQtdParada(cam.getLetrasDoCaminho().size());
															listaCaminho.add(cam);	
															
														}else {
															cam.somaTotalEP(v4.getespaco_tempo());
															cam.setletrasDoCaminho(v4.getDestino().getNome());
															UniversoVertice verticeAuxiliar5 = v4.getDestino();																						
																		
															for(Aresta v5:verticeAuxiliar5.getRotas()) {//correndo até a ultima possibilidade de nó(quarto nó)
																if(!cam.isCaminhoValido()) {
																	if(v5.getDestino().getNome().equalsIgnoreCase(verticeDestino.getNome())) {
																		cam.setCaminhoValido(true);
																		cam.somaTotalEP(v5.getespaco_tempo());
																		cam.setletrasDoCaminho(v5.getDestino().getNome());
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
									}
								}
							}	
						}
					}					
				}
			}
			primeiroIndiceOrigem(listaCaminho, verticeOrigem );
		}
	
	
	public void calculaCaminhoBB(UniversoVertice verticeOrigem, UniversoVertice verticeDestino,List<Caminho> listaCaminho) {
		
		
		for(Aresta v:verticeOrigem.getRotas()) {
			Caminho cam = new Caminho() ;//por conta disso não consegui fazer recursividade
			if(!cam.isCaminhoValido()) {
				if(v.getDestino().getNome().equalsIgnoreCase(verticeDestino.getNome())) {
					cam.setCaminhoValido(true);
					cam.somaTotalEP(v.getespaco_tempo());
					cam.setletrasDoCaminho(v.getDestino().getNome());
					cam.setQtdParada(cam.getLetrasDoCaminho().size());
					listaCaminho.add(cam);				
				}else {
					cam.somaTotalEP(v.getespaco_tempo());
					cam.setletrasDoCaminho(v.getDestino().getNome());
					UniversoVertice verticeAuxiliar = v.getDestino();
					
					for(Aresta v2:verticeAuxiliar.getRotas()) {//segundo nó
						if(!cam.isCaminhoValido() && v2.getespaco_tempo()==20) {
							if(v2.getDestino().getNome().equalsIgnoreCase(verticeDestino.getNome())) {
								cam.setCaminhoValido(true);
								cam.somaTotalEP(v2.getespaco_tempo());
								cam.setletrasDoCaminho(v2.getDestino().getNome());
								cam.setQtdParada(cam.getLetrasDoCaminho().size());
								listaCaminho.add(cam);
							}else {
								cam.somaTotalEP(v2.getespaco_tempo());
								cam.setletrasDoCaminho(v2.getDestino().getNome());
								UniversoVertice verticeAuxiliar3 = v2.getDestino();
								
								for(Aresta v3:verticeAuxiliar3.getRotas()) {//terceiro nó
									if(!cam.isCaminhoValido()) {
										if(v3.getDestino().getNome().equalsIgnoreCase(verticeDestino.getNome())) {
											cam.setCaminhoValido(true);
											cam.somaTotalEP(v3.getespaco_tempo());
											cam.setletrasDoCaminho(v3.getDestino().getNome());
											cam.setQtdParada(cam.getLetrasDoCaminho().size());
											listaCaminho.add(cam);													
										}else {
											cam.somaTotalEP(v3.getespaco_tempo());
											cam.setletrasDoCaminho(v3.getDestino().getNome());
											UniversoVertice verticeAuxiliar4 = v3.getDestino();
											
											for(Aresta v4:verticeAuxiliar4.getRotas()) {//quarto nó
												if(!cam.isCaminhoValido()) {
													if(v4.getDestino().getNome().equalsIgnoreCase(verticeDestino.getNome())) {
														cam.setCaminhoValido(true);
														cam.somaTotalEP(v4.getespaco_tempo());
														cam.setletrasDoCaminho(v4.getDestino().getNome());
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
					}		
				}
			}
		}
		primeiroIndiceOrigem(listaCaminho, verticeOrigem );
	}
	
	
public void calculaCaminhoEE(UniversoVertice verticeOrigem, UniversoVertice verticeDestino,List<Caminho> listaCaminho) {
		Caminho cam1 = new Caminho();
		cam1.setletrasDoCaminho(verticeOrigem.getNome());
		
		for(Aresta v:verticeOrigem.getRotas()) {
			Caminho cam = new Caminho() ;//por conta disso não consegui fazer recursividade
			if(!cam.isCaminhoValido()) {
				if(v.getDestino().getNome().equalsIgnoreCase(verticeDestino.getNome())) {
					cam.setCaminhoValido(true);
					cam.somaTotalEP(v.getespaco_tempo());
					cam.setletrasDoCaminho(v.getDestino().getNome());
					cam.setQtdParada(cam.getLetrasDoCaminho().size());
					listaCaminho.add(cam);				
				}else {
					cam.somaTotalEP(v.getespaco_tempo());
					cam.setletrasDoCaminho(v.getDestino().getNome());
					UniversoVertice verticeAuxiliar = v.getDestino();
					
					for(Aresta v2:verticeAuxiliar.getRotas()) {//segundo nó
						if(!cam.isCaminhoValido()) {
							if(v2.getDestino().getNome().equalsIgnoreCase(verticeDestino.getNome())) {
								cam.setCaminhoValido(true);
								cam.somaTotalEP(v2.getespaco_tempo());
								cam.setletrasDoCaminho(v2.getDestino().getNome());
								cam.setQtdParada(cam.getLetrasDoCaminho().size());
								listaCaminho.add(cam);
							}else {
								cam.somaTotalEP(v2.getespaco_tempo());
								cam.setletrasDoCaminho(v2.getDestino().getNome());
								UniversoVertice verticeAuxiliar3 = v2.getDestino();
								
								for(Aresta v3:verticeAuxiliar3.getRotas()) {//terceiro nó
									if(!cam.isCaminhoValido() && v3.getespaco_tempo() == 20) {
										if(v3.getDestino().getNome().equalsIgnoreCase(verticeDestino.getNome())) {
											cam.setCaminhoValido(true);
											cam.somaTotalEP(v3.getespaco_tempo());
											cam.setletrasDoCaminho(v3.getDestino().getNome());
											cam.setQtdParada(cam.getLetrasDoCaminho().size());
											listaCaminho.add(cam);													
										}else {
											cam.somaTotalEP(v3.getespaco_tempo());
											cam.setletrasDoCaminho(v3.getDestino().getNome());
											UniversoVertice verticeAuxiliar4 = v3.getDestino();
											
											for(Aresta v4:verticeAuxiliar4.getRotas()) {//quarto nó
												if(!cam.isCaminhoValido()) {
													if(v4.getDestino().getNome().equalsIgnoreCase(verticeDestino.getNome())) {
														cam.setCaminhoValido(true);
														cam.somaTotalEP(v4.getespaco_tempo());
														cam.setletrasDoCaminho(v4.getDestino().getNome());
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
					}		
				}
			}
		}
		
		primeiroIndiceOrigem(listaCaminho, verticeOrigem );
	}
	
	public void menorCaminho(List<Caminho> listaCaminho) {
		int menor = 700;
		int indice=0,y;
		for(int i=0; i<listaCaminho.size();i++) {
			y=i+1;
			if(listaCaminho.get(i).getTotalEspacoTempo()<=menor) {
					menor = listaCaminho.get(i).getTotalEspacoTempo();
					indice = i;				
			}			
		}
		if(!listaCaminho.isEmpty()) {
			listaCaminho.get(indice).setMenorCaminho("VIAGEM RÁPIDA");
		}
		
		
	}
	

	
	public void primeiroIndiceOrigem(List<Caminho> listaCaminho, UniversoVertice verticeOrigem ) {
		
		for(int i=0; i<listaCaminho.size();i++) {
			listaCaminho.get(i).getLetrasDoCaminho().add(0, verticeOrigem.getNome());
		}
	}
}
		
	

