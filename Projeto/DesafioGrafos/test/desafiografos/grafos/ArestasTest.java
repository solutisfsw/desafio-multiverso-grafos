/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafiografos.grafos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.naming.InvalidNameException;
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
public class ArestasTest
{
    private Arestas colecao;
    
    public ArestasTest()
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
        this.colecao = new Arestas();
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of criarOuObter method, of class Arestas.
     */
    @Test
    public void testCriarOuObter()
    {
        System.out.println("criarOuObter");
        /**
         * Primeiro garante que a coleção está vazia
         */
        this.colecao.Limpar();
        try
        {
            Vertice v1 = new Vertice("A");
            Vertice v2 = new Vertice("B");
            int distancia = 10;
            Aresta expResult = new Aresta(v1,v2,distancia);
            /* primeiro vamos testar a criação de um item novo*/
            Aresta result = this.colecao.criarOuObter(v1, v2, distancia);
            assertEquals(String.format("criarOuObter([%s],[%s])=%s; Esperado=[%s]",v1,v2,result, expResult), expResult,result);
            assertFalse("Foi adicionado apenas um item, porém a coleção contém mais de um",this.colecao.contagem()>1);
            assertFalse("Foi adicionado um item, porém a coleção contém menos de um",this.colecao.contagem()<1);
            result = this.colecao.criarOuObter(v1, v2, distancia);
            /* agora testamos a obtenção de um item que já existe */
            assertEquals(String.format("criarOuObter([%s],[%s])=%s; Esperado=[%s]",v1,v2,result, expResult), expResult,result);
            /* Se o item já existia, significa que não pode ter sido criado um item novo */
            assertFalse("Foi adicionado um item que já existia, porém um item duplicado foi criado",this.colecao.contagem()>1);
        }
        catch (InvalidNameException ex)
        {
            fail("Falha na execução: " + ex.toString());
        }
    }

    /**
     * Test of getPorOrigem method, of class Arestas.
     */
    @Test
    public void testObterPorOrigem()
    {
        System.out.println("ObterPorOrigem");
        /**
         * Primeiro garante que a coleção está vazia
         */
        this.colecao.Limpar();
        try
        {
            /* agora populamos a coleção com alguns itens diverssos */
            Vertice v1 = new Vertice("A");
            Vertice v2 = new Vertice("B");
            Vertice v3 = new Vertice("C");
            Vertice[] vertices ={v1,v2,v3};
            int distancia = 10;
            Aresta result;
            this.colecao.criarOuObter(v1, v2, distancia);
            this.colecao.criarOuObter(v1, v3, distancia);
            this.colecao.criarOuObter(v2, v1, distancia);
            this.colecao.criarOuObter(v2, v3, distancia);
            this.colecao.criarOuObter(v3, v1, distancia);
            this.colecao.criarOuObter(v3, v2, distancia);
            
            /* Agora temos cada vértice ligado a cada um dos outros, excetuando ele mesmo */
            List<Aresta> arestas;
            List<Vertice> esperados;
            
            for(Vertice v:vertices)
            {
                esperados = new ArrayList<>(Arrays.asList(vertices));
                esperados.remove(v);
                arestas = this.colecao.getPorOrigem(v);
                for(Aresta a:arestas)
                {
                    Vertice destino = a.getDestino();
                    assertTrue(String.format("A aresta %s não deveria existir ou foi duplicada", a),esperados.contains(destino));
                    esperados.remove(destino);
                }
                assertEquals("O número de arestas obtido foi menor que o número de atestas esperado.", 0, esperados.size());
            }
        }
        catch (InvalidNameException ex)
        {
            fail("Falha na execução: " + ex.toString());
        }
    }    
}
