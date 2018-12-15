/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafiografos.graficos;

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
public class Ponto2DTest
{
    
    public Ponto2DTest()
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
     * Test of toString method, of class Ponto2D.
     */
    @Test
    public void testToString()
    {
        System.out.println("toString");
        /* Esse método não precisa ser testado */
    }

    /**
     * Test of getDeslocamento method, of class Ponto2D.
     */
    @Test
    public void testObterDeslocamento()
    {
        System.out.println("obterDeslocamento");
        int incrementoX = 10;
        int incrementoY = 20;
        Ponto2D instance = new Ponto2D(100,100);
        Ponto2D expResult = new Ponto2D(110,120);
        Ponto2D result = instance.getDeslocamento(incrementoX, incrementoY);
        assertEquals(String.format("%s.getDeslocamento(%d,%d)=%s; Esperado=%s",instance,incrementoX, incrementoY,expResult,result),expResult, result);
    }

    /**
     * Test of equals method, of class Ponto2D.
     */
    @Test
    public void testEquals()
    {
        System.out.println("equals");
        Ponto2D ponto1 = new Ponto2D(10,20);
        Ponto2D ponto2 = new Ponto2D(10,20);
        assertTrue("Objetos iguais foram identificados como se fossem diferentes", ponto1.equals(ponto2));
        ponto2 = new Ponto2D(20,10);
        assertFalse("Objetos diferentes foram identificados como se fossem iguais", ponto1.equals(ponto2));
    }

    /**
     * Test of hashCode method, of class Ponto2D.
     */
    @Test
    public void testHashCode()
    {
        System.out.println("hashCode");
        /* Esse método não precisa ser testado*/
    }
    
}
