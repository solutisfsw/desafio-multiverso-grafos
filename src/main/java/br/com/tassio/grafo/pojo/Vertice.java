package br.com.tassio.grafo.pojo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Vertice implements Serializable {

	private static final long serialVersionUID = 1873116080715061595L;

	private String nome = "";
	private Set<Rota> listaRota = new HashSet<Rota>();

	public Vertice(String nome) {
		this.nome = nome.trim().toUpperCase();
	}

	public String getNome() {
		return nome;
	}

	public Set<Rota> getListaRota() {
		return listaRota;
	}

	public void setListaRota(Set<Rota> listaRota) {
		this.listaRota = listaRota;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertice other = (Vertice) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nome;
	}

}
