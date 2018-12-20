package br.com.tassio.grafo.multiverso.pojo;

public class Rota {

	private String vertice;
	private int distancia;

	public Rota(Vertice vertice, int distancia) {
		this.vertice = vertice.getNome();
		this.distancia = distancia;
	}

	public String getNomeVertice() {
		return vertice;
	}

	public int getDistancia() {
		return distancia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((vertice == null) ? 0 : vertice.hashCode());
		result = prime * result + distancia;
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
		Rota other = (Rota) obj;
		if (vertice == null) {
			if (other.vertice != null)
				return false;
		} else if (!vertice.equals(other.vertice))
			return false;
		if (distancia != other.distancia)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Rota " + vertice + ": " + distancia;
	}

}
