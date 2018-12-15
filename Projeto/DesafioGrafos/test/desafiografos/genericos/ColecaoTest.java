/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafiografos.genericos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Henrique
 */
public class ColecaoTest
{
    private Colecao<Modelo> colecao;
    
    private class Modelo extends ObjetoNomeado
    {
        public Modelo(String nome)
        {
            this.nome = nome;
        }
        
        @Override
        public int compareTo(IObjetoNomeado o)
        {
            if (o == null)
            {
                return 1;
            }
            else if (this.nome == null)
            {
                return (o.getNome() == null) ? 0 : -1;
            }
            else
            {
                return this.nome.compareTo(o.getNome());
            }
        }
        
    }
    
    
    public ColecaoTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
        this.colecao = new Colecao<>();
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of adicionarOuObter method, of class Colecao.
     */
    @Test
    public void testAdicionarOuObter()
    {
        System.out.println("adicionarOuObter");
        /**
         * Primeiro garante que a coleção está vazia
         */
        this.colecao.Limpar();
        Modelo expResult = new Modelo("Item1");
        /**
         * Primeiro testamos a adição de um novo item
         */
        Modelo result = this.colecao.adicionarOuObter(expResult);
        assertEquals("O método adicionarOuObter(IObjetoNomeado) retornou um objeto diferente daquele que foi adicionado",expResult, result);
        /**
         * Agora testamos a obtenção de um item que já foi adicionado anteriormente
         */
        result = this.colecao.adicionarOuObter(expResult);
        assertEquals("O método adicionarOuObter(IObjetoNomeado) retornou um objeto diferente daquele que foi adicionado",expResult, result);
    }

    /**
     * Test of contagem method, of class Colecao.
     */
    @Test
    public void testContagem()
    {
        System.out.println("contagem");

        /**
         * Primeiro garante que a coleção está vazia
         */
        this.colecao.Limpar();

        assertEquals("O método contagem() deveria ter retornado o valor inicial 0",0, this.colecao.contagem());
        
        for (int item=0;item<5;item++)
        {
            Modelo novoItem = new Modelo(String.format("Item%d", item));
            this.colecao.adicionarOuObter(novoItem);
        }
        int result = this.colecao.contagem();
        assertEquals(String.format("O método contagem() deveria ter retornado o valor 5 porém %d foi obtido",result),5, result);
    }

    /**
     * Test of contem method, of class Colecao.
     */
    @Test
    public void testContem()
    {
        System.out.println("contem");

        Modelo item1 = new Modelo("Item1");
        Modelo item2 = new Modelo("Item10");

        /**
         * Primeiro garante que a coleção está vazia
         */
        this.colecao.Limpar();
        
        /**
         * Agora adicionamos alguns itens conhecidos para o teste
         */
        for (int item=0;item<5;item++)
        {
            Modelo novoItem = new Modelo(String.format("Item%d", item));
            this.colecao.adicionarOuObter(novoItem);
        }

        assertTrue("O objeto com nome \"Item1\" deveria existir na lista, mas não foi encontrado",this.colecao.contem(item1));
        assertFalse("O objeto com nome \"Item10\" não deveria existir na lista, mas foi encontrado",this.colecao.contem(item2));
    }

    /**
     * Test of iterator method, of class Colecao.
     */
    @Test
    public void testIterator()
    {
        List<String> elementos = new ArrayList<>();
        
        System.out.println("iterator");
        /**
         * Primeiro garante que a coleção está vazia
         */
        this.colecao.Limpar();
        /**
         * Agora adicionamos alguns itens conhecidos para o teste
         */
        for (int item=1;item<=5;item++)
        {
            String nomeAtual = String.format("Item%d",item);
            elementos.add(nomeAtual);
            Modelo novoItem = new Modelo(nomeAtual);
            this.colecao.adicionarOuObter(novoItem);
        }

        int item = 0;
        
        Iterator<Modelo> it = this.colecao.iterator();
        while (it.hasNext())
        {
            item++;
            Modelo objeto = it.next();
            assertNotNull(String.format("O objeto retornado pelo Iterator na iteração %d de 5 é nulo",item),objeto);
            int pos = elementos.indexOf(objeto.getNome());
            assertNotEquals(String.format("O nome do objeto retornado pelo Iterator na iteração %d de 5 não correponde ao valor esperado",item), -1, pos);
            elementos.remove(pos);
        }
        assertEquals(String.format("Deveriam ter sido executadas 5 iterações, porém ocorreram %d",item), 5,item);
    }    
}
