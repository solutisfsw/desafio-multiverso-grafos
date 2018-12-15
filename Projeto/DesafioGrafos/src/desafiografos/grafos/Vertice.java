/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafiografos.grafos;

import desafiografos.genericos.IObjetoNomeado;
import desafiografos.genericos.ObjetoNomeado;
import javax.naming.InvalidNameException;

/**
 *
 * @author Henrique
 */
public class Vertice extends ObjetoNomeado
{

    /**
     * Cria um novo vértice com o nome especificado
     * @param nome Nome do novo vértice
     * @throws InvalidNameException  Ocorre quando o nome do novo vértice é inválido.
     * O nome do novo vértice não pode ser uma string vazia, não pode ter somente
     * espaços em branco e não pode conter o caractere nulo. Espaços em branco no
     * início ou no fim do nome serão removidos.
     */
    public Vertice(String nome) throws InvalidNameException
    {
        if (nome.contains("\0"))
        {
            throw new InvalidNameException("O caractere nulo não pode ser usado como parte do nome do vértice.");
        }
        else if(nome == null)
        {
            throw new InvalidNameException("O nome do vértice não pode ser uma string nula.");
        }
        else if(nome.trim().equals(""))
        {
            throw new InvalidNameException("O nome do vértice não pode ser uma string vazia nem pode conter apenas espaços em branco.");
        }
        nome = nome.trim();
        /**
         * O nome do vértice serve para sua identificação de forma exclusiva,
         * permitindo que dois vértices iguais sejam identificados de forma
         * simples e rápida. Ele também serve para a representação visual.
         * 
         * O nome pode ser usado para comparação de objetos, inclusive para ordenação.
         */
        this.nome = nome.toUpperCase();
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
        else
        {
            result =  this.nome.compareTo(o.getNome());
        }
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Vertice)
        {
            return this.compareTo((Vertice)obj) == 0;
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
