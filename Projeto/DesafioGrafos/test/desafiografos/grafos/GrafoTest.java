/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafiografos.grafos;

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
public class GrafoTest
{
    public GrafoTest()
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
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of getVertices method, of class Grafo.
     */
    @Test
    public void testGetVertices()
    {
        System.out.println("getVertices");
        Grafo instance = new Grafo();
        Vertices result = instance.getVertices();
        assertNotNull("O método getVertices() não pode retornar um valor nulo", result);
    }

    /**
     * Test of getArestas method, of class Grafo.
     */
    @Test
    public void testGetArestas()
    {
        System.out.println("getArestas");
        Grafo instance = new Grafo();
        Arestas result = instance.getArestas();
        assertNotNull("O método getArestas() não pode retornar um valor nulo", result);
    }

    /**
     * Test of criarOuObterAresta method, of class Grafo.
     */
    @Test
    public void testCriarOuObterAresta() throws Exception
    {
        System.out.println("criarOuObterAresta");

        Grafo grafo = new Grafo();
        try
        {
            String  v1 = "A";
            String v2 = "B";
            int distancia = 10;
            Aresta expResult = new Aresta(new Vertice(v1),new Vertice(v2),distancia);
            /* primeiro vamos testar a criação de um item novo*/
            Aresta result = grafo.criarOuObterAresta(v1, v2, distancia);
            assertEquals(String.format("criarOuObterAresta([%s],[%s])=%s; Esperado=[%s]",v1,v2,result, expResult), expResult,result);
            assertFalse("Foi adicionado apenas um item, porém a coleção contém mais de um",grafo.getArestas().contagem()>1);
            assertFalse("Foi adicionado um item, porém a coleção contém menos de um",grafo.getArestas().contagem()<1);
            result = grafo.criarOuObterAresta(v1, v2, distancia);
            /* agora testamos a obtenção de um item que já existe */
            assertEquals(String.format("criarOuObterAresta([%s],[%s])=%s; Esperado=[%s]",v1,v2,result, expResult), expResult,result);
            /* Se o item já existia, significa que não pode ter sido criado um item novo */
            assertFalse("Foi adicionado um item que já existia, porém um item duplicado foi criado",grafo.getArestas().contagem()>1);
        }
        catch (InvalidNameException ex)
        {
            fail("Falha na execução: " + ex.toString());
        }
    }

    /**
     * Test of criarOuObterVertice method, of class Grafo.
     */
    @Test
    public void testCriarOuObterVertice() throws Exception
    {
        System.out.println("criarOuObterVertice");
        Grafo grafo = new Grafo();
        try
        {
            /* primeiro vamos testar a criação de um item novo*/
            Vertice expResult = new Vertice("A");
            Vertice result = grafo.criarOuObterVertice("A");
            assertEquals(String.format("criarOuObterVertice(\"A\")=%s; Esperado=[%s]",result, expResult), expResult,result);
            assertFalse("Foi adicionado apenas um item, porém a coleção contém mais de um",grafo.getVertices().contagem()>1);
            assertFalse("Foi adicionado um item, porém a coleção contém menos de um",grafo.getVertices().contagem()<1);
            /* agora testamos a obtenção de um item que já existe */
            result = grafo.criarOuObterVertice("A");
            assertEquals(String.format("criarOuObterVertice(\"A\")=%s; Esperado=[%s]",result, expResult), expResult,result);
            /* Se o item já existia, significa que não pode ter sido criado um item novo */
            assertFalse("Foi adicionado um item que já existia, porém um item duplicado foi criado",grafo.getVertices().contagem()>1);
        }
        catch (InvalidNameException ex)
        {
            fail("Falha na execução: " + ex.toString());
        }
    }

    /**
     * Test of getCaminhos method, of class Grafo.
     */
    @Test
    public void testObterCaminhos_6args_1() throws Exception
    {
        System.out.println("ObterCaminhos");
        Grafo grafo = new Grafo();
        grafo.criarOuObterAresta("A", "B", 1);
        grafo.criarOuObterAresta("A", "C", 2);
        grafo.criarOuObterAresta("B", "A", 4);
        grafo.criarOuObterAresta("B", "C", 8);
        grafo.criarOuObterAresta("C", "A", 16);
        grafo.criarOuObterAresta("C", "B", 32);
        List<Caminho> result;
        try
        {
            /* Uma busca para um vértice que não pertence ao grafo deve disparar uma exceção */
            grafo.getCaminhos("A", "D", null, 0, 0, Grafo.OpcoesDeBusca.TodasAsRotas);
            fail("Não deve ser permitida a busca por uma rota para um vértice que não pertence ao grafo");
        }
        catch(Exception ex){/* nada para fazer, esse é o comportamento esperado */}
        
        result = grafo.getCaminhos("A", "C", null, 0, 0, Grafo.OpcoesDeBusca.TodasAsRotas);
        assertEquals("O número de rotas está inconsistente",2, result.size());
        result = grafo.getCaminhos("A", "C", "B", 0, 0, Grafo.OpcoesDeBusca.TodasAsRotas);
        assertEquals("O número de rotas está inconsistente",1, result.size());
        result = grafo.getCaminhos("A", "C", null, 0, 0, Grafo.OpcoesDeBusca.MenorDistancia);
        assertEquals("O número de rotas está inconsistente",1, result.size());
        assertEquals("A menor rota está inconsistente",2, result.get(0).getDistancia());
        result = grafo.getCaminhos("A", "C", null, 0, 0, Grafo.OpcoesDeBusca.MaiorDistancia);
        assertEquals("O número de rotas está inconsistente",1, result.size());
        assertEquals("A maior rota está inconsistente",9, result.get(0).getDistancia());
    }

    /**
     * Test of getCaminhos method, of class Grafo.
     */
    @Test
    public void testObterCaminhos_6args_2() throws Exception
    {
        System.out.println("ObterCaminhos");
        System.out.println("ObterCaminhos");
        Vertice va = new Vertice("A");
        Vertice vb = new Vertice("B");
        Vertice vc = new Vertice("C");
        Vertice vd = new Vertice("D");
        Grafo grafo = new Grafo();
        grafo.criarOuObterAresta("A", "B", 1);
        grafo.criarOuObterAresta("A", "C", 2);
        grafo.criarOuObterAresta("B", "A", 4);
        grafo.criarOuObterAresta("B", "C", 8);
        grafo.criarOuObterAresta("C", "A", 16);
        grafo.criarOuObterAresta("C", "B", 32);
        List<Caminho> result;
        try
        {
            /* Uma busca para um vértice que não pertence ao grafo deve disparar uma exceção */
            grafo.getCaminhos("A", "D", null, 0, 0, Grafo.OpcoesDeBusca.TodasAsRotas);
            fail("Não deve ser permitida a busca por uma rota para um vértice que não pertence ao grafo");
        }
        catch(Exception ex){/* nada para fazer, esse é o comportamento esperado */}
        
        result = grafo.getCaminhos(va, vc, null, 0, 0, Grafo.OpcoesDeBusca.TodasAsRotas);
        assertEquals("O número de rotas está inconsistente",2, result.size());
        result = grafo.getCaminhos(va, vc, vb, 0, 0, Grafo.OpcoesDeBusca.TodasAsRotas);
        assertEquals("O número de rotas está inconsistente",1, result.size());
        result = grafo.getCaminhos(va, vc, null, 0, 0, Grafo.OpcoesDeBusca.MenorDistancia);
        assertEquals("O número de rotas está inconsistente",1, result.size());
        assertEquals("A menor rota está inconsistente",2, result.get(0).getDistancia());
        result = grafo.getCaminhos(va, vc, null, 0, 0, Grafo.OpcoesDeBusca.MaiorDistancia);
        assertEquals("O número de rotas está inconsistente",1, result.size());
        assertEquals("A maior rota está inconsistente",9, result.get(0).getDistancia());
    }
    
}
