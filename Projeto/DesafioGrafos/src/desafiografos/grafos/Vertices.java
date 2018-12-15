/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafiografos.grafos;

import desafiografos.genericos.Colecao;
import javax.naming.InvalidNameException;

/**
 *
 * @author Henrique
 */
public class Vertices  extends Colecao<Vertice>
{
    /**
     * Retorna o vértice com o nome especificado. Caso o vértice não exista, será criado.
     * @param nome Nome do vértice
     * @return Retorna um vértice existente ou o vértice que acabou de ser criado
     * @throws InvalidNameException Ocorre caso o nome do vértice seja inválido
     * Os nomes dos vértices não podem ser strings vazias, não podem ter somente
     * espaços em branco e não podem conter o caractere nulo. Espaços em branco no
     * início ou no fim do nome serão removidos.
     */
    Vertice criarOuObter(String nome) throws InvalidNameException
    {
        Vertice v = new Vertice(nome);
        return super.adicionarOuObter(v);
    }    
}
