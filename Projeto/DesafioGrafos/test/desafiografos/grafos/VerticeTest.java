/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafiografos.grafos;

import desafiografos.genericos.IObjetoNomeado;
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
public class VerticeTest
{
    
    public VerticeTest()
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
     * Test of compareTo method, of class Vertice.
     */
    @Test
    public void testCompareTo()
    {
        System.out.println("compareTo");
        try
        {
            Vertice v1 = new Vertice("A");
            Vertice v2 = new Vertice("A");
            Vertice v3 = new Vertice("B");

            int result;
            result = v1.compareTo(v2);
            assertEquals("O resultado da comparação está inconsistente", 0, result);
            result = v1.compareTo(v3);
            assertEquals("O resultado da comparação está inconsistente", -1, result);
            result = v3.compareTo(v1);
            assertEquals("O resultado da comparação está inconsistente", 1, result);
        }
        catch(Exception ex)
        {
            fail("Falha na execução do teste: " + ex.toString());
        }
    }

    /**
     * Test of equals method, of class Vertice.
     */
    @Test
    public void testEquals()
    {
        System.out.println("equals");
        try
        {
            Vertice v1 = new Vertice("A");
            Vertice v2 = new Vertice("A");
            Vertice v3 = new Vertice("B");

            boolean result;
            result = v1.equals(v2);
            assertTrue("O resultado da comparação está inconsistente", result);
            result = v1.equals(v3);
            assertFalse("O resultado da comparação está inconsistente", result);
        }
        catch(Exception ex)
        {
            fail("Falha na execução do teste: " + ex.toString());
        }
    }

    /**
     * Test of hashCode method, of class Vertice.
     */
    @Test
    public void testHashCode()
    {
        System.out.println("hashCode");
        /* Esse método não precisa ser testado */
    }
    
}
