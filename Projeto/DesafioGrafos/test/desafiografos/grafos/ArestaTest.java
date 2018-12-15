/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafiografos.grafos;

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
public class ArestaTest
{
    
    public ArestaTest()
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
     * Test of getOrigem method, of class Aresta.
     */
    @Test
    public void testGetOrigem()
    {
        System.out.println("getOrigem");
        try
        {
            Vertice v1 = new Vertice("A");
            Vertice v2 = new Vertice("B");
            Vertice v3 = new Vertice("C");
            Vertice v4 = new Vertice("D");
            Aresta a1 = new Aresta(v1,v4,10);
            Aresta a2 = new Aresta(v2,v3,20);
            Aresta a3 = new Aresta(v3,v2,10);
            Aresta a4 = new Aresta(v4,v1,10);
            
            boolean result;
            result = a1.getOrigem().equals(v1);
            assertTrue(String.format("[%s].getOrigem() = [%s], esperado [%s]",a1,a1.getOrigem(),v1),result);
            result = a2.getOrigem().equals(v2);
            assertTrue(String.format("[%s].getOrigem() = [%s], esperado [%s]",a1,a1.getOrigem(),v2),result);
            result = a3.getOrigem().equals(v3);
            assertTrue(String.format("[%s].getOrigem() = [%s], esperado [%s]",a1,a1.getOrigem(),v3),result);
            result = a4.getOrigem().equals(v4);
            assertTrue(String.format("[%s].getOrigem() = [%s], esperado [%s]",a1,a1.getOrigem(),v4),result);
        }
        catch (InvalidNameException ex)
        {
            fail("Falha não prevista: " + ex.toString());
        }
    }

    /**
     * Test of getDestino method, of class Aresta.
     */
    @Test
    public void testGetDestino()
    {
        System.out.println("getDestino");
        try
        {
            Vertice v1 = new Vertice("A");
            Vertice v2 = new Vertice("B");
            Vertice v3 = new Vertice("C");
            Vertice v4 = new Vertice("D");
            Aresta a1 = new Aresta(v1,v4,10);
            Aresta a2 = new Aresta(v2,v3,20);
            Aresta a3 = new Aresta(v3,v2,10);
            Aresta a4 = new Aresta(v4,v1,10);
            
            boolean result;
            result = a1.getDestino().equals(v4);
            assertTrue(String.format("[%s].getDestino() = [%s], esperado [%s]",a1,a1.getDestino(),v4),result);
            result = a2.getDestino().equals(v3);
            assertTrue(String.format("[%s].getDestino() = [%s], esperado [%s]",a2,a2.getDestino(),v3),result);
            result = a3.getDestino().equals(v2);
            assertTrue(String.format("[%s].getDestino() = [%s], esperado [%s]",a3,a3.getDestino(),v2),result);
            result = a4.getDestino().equals(v1);
            assertTrue(String.format("[%s].getDestino() = [%s], esperado [%s]",a4,a4.getDestino(),v1),result);
        }
        catch (InvalidNameException ex)
        {
            fail("Falha não prevista: " + ex.toString());
        }
    }

    /**
     * Test of getDistancia method, of class Aresta.
     */
    @Test
    public void testGetDistancia()
    {
        System.out.println("getDistancia");
        try
        {
            Aresta a1 = new Aresta(new Vertice("A"),new Vertice("B"),10);
            Aresta a2 = new Aresta(new Vertice("A"),new Vertice("B"),20);
            Aresta a3 = new Aresta(new Vertice("B"),new Vertice("A"),10);
            Aresta a4 = new Aresta(new Vertice("A"),new Vertice("B"),10);
            Aresta a5 = new Aresta(new Vertice("A"),new Vertice("A"),10);
            
            int result;
            result = a1.compareTo(a2);
            assertTrue(String.format("[%s].compareTo([%s]) = %d, esperado <0",a1,a2, result),result < 0);
            result = a1.compareTo(a3);
            assertTrue(String.format("[%s].compareTo([%s]) = %d, esperado <0",a1,a3, result),result < 0);
            result = a1.compareTo(a4);
            assertTrue(String.format("[%s].compareTo([%s]) = %d, esperado 0",a1,a4, result),result == 0);
            result = a1.compareTo(a5);
            assertTrue(String.format("[%s].compareTo([%s]) = %d, esperado >0",a1,a5, result),result > 0);
        }
        catch (InvalidNameException ex)
        {
            fail("Falha não prevista: " + ex.toString());
        }
    }

    /**
     * Test of compareTo method, of class Aresta.
     */
    @Test
    public void testCompareTo()
    {
        System.out.println("compareTo");
        try
        {
            Aresta a1 = new Aresta(new Vertice("A"),new Vertice("B"),10);
            Aresta a2 = new Aresta(new Vertice("A"),new Vertice("B"),20);
            Aresta a3 = new Aresta(new Vertice("B"),new Vertice("A"),10);
            Aresta a4 = new Aresta(new Vertice("A"),new Vertice("B"),10);
            Aresta a5 = new Aresta(new Vertice("A"),new Vertice("A"),10);
            
            int result;
            result = a1.compareTo(a2);
            assertTrue(String.format("[%s].compareTo([%s]) = %d, esperado <0",a1,a2, result),result < 0);
            result = a1.compareTo(a3);
            assertTrue(String.format("[%s].compareTo([%s]) = %d, esperado <0",a1,a3, result),result < 0);
            result = a1.compareTo(a4);
            assertTrue(String.format("[%s].compareTo([%s]) = %d, esperado 0",a1,a4, result),result == 0);
            result = a1.compareTo(a5);
            assertTrue(String.format("[%s].compareTo([%s]) = %d, esperado >0",a1,a5, result),result > 0);
        }
        catch (InvalidNameException ex)
        {
            fail("Falha não prevista: " + ex.toString());
        }
    }

    /**
     * Test of equals method, of class Aresta.
     */
    @Test
    public void testEquals()
    {
        System.out.println("equals");
        try
        {
            Aresta a1 = new Aresta(new Vertice("A"),new Vertice("B"),10);
            Aresta a2 = new Aresta(new Vertice("A"),new Vertice("B"),20);
            Aresta a3 = new Aresta(new Vertice("B"),new Vertice("A"),10);
            Aresta a4 = new Aresta(new Vertice("A"),new Vertice("B"),10);
            Aresta a5 = new Aresta(new Vertice("A"),new Vertice("A"),10);
            
            boolean result;
            result = a1.equals(a2);
            assertFalse(String.format("[%s].equals([%s]) = %b, esperado False",a1,a2, result),result);
            result = a1.equals(a3);
            assertFalse(String.format("[%s].equals([%s]) = %b, esperado False",a1,a3, result),result);
            result = a1.equals(a4);
            assertTrue(String.format("[%s].equals([%s]) = %b, esperado False",a1,a4, result),result);
            result = a1.equals(a5);
            assertFalse(String.format("[%s].equals([%s]) = %b, esperado True",a1,a5, result),result);
        }
        catch (InvalidNameException ex)
        {
            fail("Falha não prevista: " + ex.toString());
        }
    }

    /**
     * Test of hashCode method, of class Aresta.
     */
    @Test
    public void testHashCode()
    {
        System.out.println("hashCode");
        /* método não precisa ser testado*/
    }
    
}
