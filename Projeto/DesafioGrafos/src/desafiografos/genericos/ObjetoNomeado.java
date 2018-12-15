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
public abstract class ObjetoNomeado implements IObjetoNomeado
{
    protected String nome;
    protected String rotulo;
    
    @Override
    public String getNome()
    {
        return this.nome;
    }

    @Override
    public String toString()
    {
        return this.rotulo == null ? this.nome : this.rotulo;
    }

    @Override
    public String getRotulo()
    {
        return this.rotulo;
    }
    
    
}
