/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafiografos.grafos.Comparadores;

import desafiografos.grafos.Aresta;
import desafiografos.grafos.Caminho;
import desafiografos.grafos.Vertice;
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
public class ComparadorDeParadasTest
{
    
    public ComparadorDeParadasTest()
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
     * Test of compare method, of class ComparadorDeParadas.
     */
    @Test
    public void testCompare()
    {
        System.out.println("compare");
        try
        {
            Vertice v1 = new Vertice("A");
            Vertice v2 = new Vertice("B");
            Vertice v3 = new Vertice("C");
            Aresta a1 = new Aresta(v1,v2, 10);
            Aresta a2 = new Aresta(v1,v2, 5);
            Aresta a3 = new Aresta(v2,v3, 5);
            Caminho c1 = new Caminho(v1,v2,2);
            Caminho c2 = new Caminho(v1,v3,2);
            List<Aresta> arestas;
            
            arestas = new ArrayList<>(Arrays.asList(new Aresta[]{a1}));
            c1.Ramificar(arestas);
            arestas = new ArrayList<>(Arrays.asList(new Aresta[]{a2}));
            c2.Ramificar(arestas);
            arestas = new ArrayList<>(Arrays.asList(new Aresta[]{a3}));
            c2.Ramificar(arestas);
            
            ComparadorDeParadas instance = new ComparadorDeParadas();
            int expResult = -1;
            int result = instance.compare(c1, c2);
            assertEquals("O resultado da comparação entre as instâncias está incorreto",expResult, result);
        }
        catch (Exception ex)
        {
            fail("Falha na execução do teste: " + ex.toString());
        }
    }    
}
