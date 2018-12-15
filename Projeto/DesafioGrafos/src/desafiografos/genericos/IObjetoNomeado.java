/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafiografos.genericos;

/**
 *
 * @author Henrique
 */
public interface IObjetoNomeado extends Comparable<IObjetoNomeado>
{
    public String getNome();
    public String getRotulo();
}
