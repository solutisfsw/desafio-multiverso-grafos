/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafiografos.grafos;

import java.util.ArrayList;
import java.util.Arrays;
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
public class CaminhoTest
{
    Vertice v1;
    Vertice v2;
    Vertice v3;
    Aresta a1;
    Aresta a2;
    Aresta a3;
    Caminho caminho;
    boolean caminhoPronto = false;
    
    public CaminhoTest()
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
        try
        {
            this.v1 = new Vertice("A");
            this.v2 = new Vertice("B");
            this.v3 = new Vertice("C");
            this.a1 = new Aresta(this.v1,this.v2,10);
            this.a2 = new Aresta(this.v2,this.v3,10);
            this.a3 = new Aresta(this.v1,this.v3,10);
            this.caminho = new Caminho(this.v1,this.v3,3);
            this.caminhoPronto = true;

            List<Aresta> arestas;
            arestas = new ArrayList<>(Arrays.asList(new Aresta[]{a1}));
            caminho.Ramificar(arestas);
            arestas = new ArrayList<>(Arrays.asList(new Aresta[]{a2}));
            caminho.Ramificar(arestas);
        }
        catch (Exception ex)
        {
            fail("Erro configurando a instância de teste: " + ex.toString());
        }
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of getUltimoVertice method, of class Caminho.
     */
    @Test
    public void testUltimoVertice()
    {
        System.out.println("ultimoVertice");
        assertTrue("A instância de testes não foi carregada",this.caminhoPronto);
        assertEquals("O último vértice não corresponde ao esperado", this.caminho.getUltimoVertice(), this.v3);
    }

    /**
     * Test of concluido method, of class Caminho.
     */
    @Test
    public void testConcluido()
    {
        System.out.println("concluido");
        assertTrue("A instância de testes não foi carregada",this.caminhoPronto);
        assertTrue("O método concluido() retornou False quando deveria retornar True", this.caminho.concluido());
        Caminho novoCaminho = new Caminho(this.v1,this.v2, 3);
        assertFalse("O método concluido() retornou True quando deveria retornar False", novoCaminho.concluido());
    }

    /**
     * Test of getEncontrado method, of class Caminho.
     */
    @Test
    public void testGetEncontrado()
    {
        System.out.println("getEncontrado");
        assertTrue("A instância de testes não foi carregada",this.caminhoPronto);
        assertTrue("O método getEncontrado() retornou False quando deveria retornar True", this.caminho.getEncontrado());
        Caminho novoCaminho = new Caminho();
        assertFalse("O método getEncontrado() retornou True quando deveria retornar False", novoCaminho.getEncontrado());
    }

    /**
     * Test of Ramificar method, of class Caminho.
     */
    @Test
    public void testRamificar()
    {
        System.out.println("Ramificar");
        assertTrue("A instância de testes não foi carregada",this.caminhoPronto);

        Caminho instance = new Caminho(this.v1, this.v3, 3);
        List<Aresta> arestas;
        arestas = new ArrayList<>(Arrays.asList(new Aresta[]{this.a1, this.a3}));
        List<Caminho> caminhos = instance.Ramificar(arestas);
        assertEquals("O número de caminhos ramificados está inconsistente",2,caminhos.size());
        Caminho c1 = caminhos.get(0);
        Caminho c2 = caminhos.get(1);
        Caminho aux;
        if(c1.getUltimoVertice() == null || c2.getUltimoVertice() == null)
        {
            fail("Um ou mais caminhos ramificados possui último vértice nulo");
        }
        else if (c2.getUltimoVertice().equals(this.v2))
        {
            aux=c1;
            c1=c2;
            c2=aux;
        }
        assertFalse("O caminho foi encerrado prematuramente",c1.concluido());
        assertFalse("O caminho relatou um falso positivo",c1.getEncontrado());
        assertTrue("O caminho nçao foi encerrado corretamente",c2.concluido());
        assertTrue("O caminho relatou um falso negativo",c2.getEncontrado());
    }

    /**
     * Test of toString method, of class Caminho.
     */
    @Test
    public void testToString()
    {
        System.out.println("toString");
        /* Esse método serve apenas para depuração e dispensa testes */ 
    }

    /**
     * Test of getRota method, of class Caminho.
     */
    @Test
    public void testObterRota()
    {
        System.out.println("obterRota");
        assertTrue("A instância de testes não foi carregada",this.caminhoPronto);
        List<Aresta> expResult = new ArrayList<>(Arrays.asList(new Aresta[]{this.a1,this.a2}));
        List<Aresta> result = this.caminho.getRota();
        assertEquals("A rota encontrada está inconsistente",expResult, result);
    }

    /**
     * Test of getDistancia method, of class Caminho.
     */
    @Test
    public void testObterDistancia()
    {
        System.out.println("obterDistancia");
        assertTrue("A instância de testes não foi carregada",this.caminhoPronto);
        int expResult = this.a1.getDistancia()+this.a2.getDistancia();
        int result = this.caminho.getDistancia();
        assertEquals("A distância do caminho não foi computada corretamente",expResult, result);
    }

    /**
     * Test of getParadas method, of class Caminho.
     */
    @Test
    public void testObterParadas()
    {
        System.out.println("obterParadas");
        assertTrue("A instância de testes não foi carregada",this.caminhoPronto);
        int expResult = 2;
        int result = this.caminho.getParadas();
        assertEquals("O número de paradas não foi computado corretamente",expResult, result);
    }

    /**
     * Test of contemAresta method, of class Caminho.
     */
    @Test
    public void testContemAresta()
    {
        System.out.println("contemAresta");
        assertTrue("A instância de testes não foi carregada",this.caminhoPronto);
        assertTrue("As arestas do caminho são inconsistentes",this.caminho.contemAresta(this.a1));
        assertTrue("As arestas do caminho são inconsistentes",this.caminho.contemAresta(this.a2));
    }

    /**
     * Test of contemVertice method, of class Caminho.
     */
    @Test
    public void testContemVertice()
    {
        System.out.println("contemVertice");
        assertTrue("A instância de testes não foi carregada",this.caminhoPronto);
        assertTrue("Os vértices do caminho são inconsistentes",this.caminho.contemVertice(this.v1));
        assertTrue("Os vértices do caminho são inconsistentes",this.caminho.contemVertice(this.v2));
        assertTrue("Os vértices do caminho são inconsistentes",this.caminho.contemVertice(this.v3));
    }

    /**
     * Test of getTrajeto method, of class Caminho.
     */
    @Test
    public void testGetTrajeto()
    {
        System.out.println("getTrajeto");
        String expResult = "A↦[10]→B↦[10]→C";
        String result = this.caminho.getTrajeto();
        assertEquals("A representação textual do trajeto não está consistente",expResult, result);
    }
    
}
