/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafiografos;

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
public class DesafioGrafosTest
{
    /**
     * Tempo máaximo de espera antes de encerrar o teste.
     * 10 segundos são mais que suficientes
     */
    private static final int TEMPO_LIMITE = 10000;
    private TestMain testador;
    
    private class TestMain implements Runnable
    {
        public boolean result;
        
        @Override
        public void run()
        {
            try
            {
                String[] args = null;
                DesafioGrafos.main(args);
                result = true;
            }
            catch(Exception ex)
            {
                result = false;
            }
        }
    }
    
    public DesafioGrafosTest()
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
        testador =  new TestMain();
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of main method, of class DesafioGrafos.
     */
    @Test
    public void testMain()
    {
        try
        {
            /** 
             * Se o main levar mais de 10 segundos para executar, significa que
             * algo errado ocorreu, possivelmente um loop infinito.
             */
            System.out.println("main");
            Thread t = new Thread(this.testador);
            t.start();
            synchronized (t)
            {
                t.wait(TEMPO_LIMITE);
            }
            if (t.isAlive())
            {
                try
                {
                    t.interrupt();
                }
                catch (Exception ex){}
            }
            assertTrue("A execução do método main(String[]) não foi concluída com sucesso",this.testador.result);
        }
        catch (Exception ex)
        {
            fail("Falha inesperada durante a execução do método main(String[]): " + ex.toString());
        }
    }
}
