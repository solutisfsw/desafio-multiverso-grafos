/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafiografos.grafos;

import desafiografos.genericos.Colecao;
import desafiografos.genericos.ListaNula;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Henrique
 */
public class Arestas extends Colecao<Aresta>
{
    private final HashMap<Vertice, ArrayList<Aresta>> mapa = new HashMap<>();
    /**
     * Singletron (Padrão de projeto) para ser devolvido sempre que uma lista
     * de arestas vazia for necessária.
     * 
     * Essa lista implementa apenas as funcionalidades mais primitivas, não
     * permitindo inclusão ou remoção de itens. A iteração é possível, porém
     * nunca retorna nenhum item.
     */
    public static final ListaNula<Aresta> LISTA_VAZIA = new ListaNula<>();
    
    Aresta criarOuObter(Vertice origem, Vertice destino, int distancia)
    {
        Aresta a = new Aresta(origem,destino, distancia);
        if (!mapa.containsKey(origem))
        {
            mapa.put(origem, new ArrayList<>());
        }
        mapa.get(origem).add(a);
        return super.adicionarOuObter(a);
    }

    List<Aresta> getPorOrigem(Vertice origem)
    {
        if (mapa.containsKey(origem))
        {
            return new ArrayList<>(mapa.get(origem));
        }
        else
        {
            return LISTA_VAZIA;
        }
    }
    
}
