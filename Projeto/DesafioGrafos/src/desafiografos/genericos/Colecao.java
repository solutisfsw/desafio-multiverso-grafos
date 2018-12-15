/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafiografos.genericos;

import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author Henrique
 * @param <Tipo> define o tipo de objeto que será armazenado e que deverá implementar a interface IObjetoNomeado
 */
public class Colecao<Tipo extends IObjetoNomeado>  implements Iterable<Tipo>
{
    protected final HashMap<String,Tipo> itens = new HashMap<>();
    
    /**
     * Inclui um novo item na coleção e devolve o item criado ou um item já existente
     * @param item Item a ser inserido. Valores nulos serão desprezados.
     * @return 
     */
    protected Tipo adicionarOuObter(Tipo item)
    {
        if (item == null)
        {
            /* Nada a fazer aqui, objetos nulos devem ser ignorados */
        }
        else if (this.itens.containsKey(item.getNome()))
        {
            /* Se já existe um item igual, simplesmente devolve o objeto correspondente */
            item = this.itens.get(item.getNome());
        }
        else
        {
            /* Se o item ainda não está no conjunto então adiciona */
            this.itens.put(item.getNome(), item);
        }
        return item;
    }

    /**
     * Retorna o número de itens na coleção
     * @return 
     */
    public int contagem()
    {
        return this.itens.size();
    }
    
    /**
     * Verifica se um item pertence à coleção
     * @param obj
     * @return 
     */
    public boolean contem(Tipo obj)
    {
        return this.itens.containsKey(obj.getNome());
    }

    @Override
    public Iterator<Tipo> iterator()
    {
        return this.itens.values().iterator();
    }
    
    /**
     * Esvazia a coleção descartando todos os itens
     */
    public void Limpar()
    {
        this.itens.clear();
    }
}
