/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafiografos.grafos;
import desafiografos.genericos.IObjetoNomeado;
import desafiografos.genericos.ObjetoNomeado;

/**
 *
 * @author Henrique
 * Define uma aresta orientada a partir de um vértice de origem e um vértice de destino
 */
public class Aresta extends ObjetoNomeado
{
    private final Vertice origem;
    private final Vertice destino;
    private final int distancia;
    
    public Aresta(Vertice origem, Vertice destino,int distancia)
    {
        /**
         * Os vértices de origem e destino identificam a orientação do grafo
         */
        this.origem=origem;
        this.destino=destino;
        this.distancia = distancia;
        /**
         * O nome da aresta serve para sua identificação de forma exclusiva,
         * permitindo que duas arestas iguais sejam identificadas de forma
         * simples e rápida. Ele também serve para a representação visual.
         * 
         * O nome não deve ser usado para a comparação de objetos, pois ele é
         * ordenado de forma alfabética simples, sem levar em consideração os
         * vértices. Isso significa que o nome permite identificar se dois objetos
         * distintos são iguais ou diferentes, mas não permite determinar qual
         * a ordem correta na ordenação.
         */
        this.nome = String.format("%s\0%d\0%s",origem.getNome() , this.distancia, destino.getNome());
        this.rotulo=String.format("%s↦[%d]→%s", origem.getNome(), this.distancia, destino.getNome());
    }
    
    /**
     * @return Retorna o vértice de origem da aresta
     */
    public Vertice getOrigem()
    {
        return origem;
    }

    /**
     * @return Retorna o vértice de destino da aresta
     */
    public Vertice getDestino()
    {
        return destino;
    }

    /**
     * @return the distancia
     */
    public int getDistancia()
    {
        return distancia;
    }

    /**
     * 
     * @param o Objeto do tipo IObjetoNomeado a ser comparado
     * @return Retorna -1, 0 ou 1, conforme a posição relativa do objeto comparado em relação ao objeto corrente
     */
    @Override
    public int compareTo(IObjetoNomeado o)
    {
        int result;
        if (o==null)
        {
            result = 1;
        }
        else if (o instanceof Aresta)
        {
            Aresta a = (Aresta)o;
            result = this.origem.compareTo(a.origem);
            if (result == 0)
            {
                result = this.destino.compareTo(a.destino);
                if (result == 0)
                {
                    result =Integer.compare(this.distancia,a.distancia);
                }
            }
        }
        else
        {
            result =  this.nome.compareTo(o.getNome());
        }
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Aresta)
        {
            return this.compareTo((Aresta)obj) == 0;
        }
        else
        {
            return false;
        }
    }

    @Override
    public int hashCode()
    {
        return this.nome.hashCode();
    }
}
