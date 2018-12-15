/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafiografos.genericos;

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
public class ObjetoNomeadoTest
{
    private Modelo modelo;
    private static final String NOME_PADRAO = "modelo1";
    private static final String NOME_DE_TESTE = "modelo2";
    
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
    
    public ObjetoNomeadoTest()
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
        this.modelo = new Modelo(NOME_PADRAO);
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of getNome method, of class ObjetoNomeado.
     */
    @Test
    public void testGetNome()
    {
        System.out.println("getNome");
        String result = this.modelo.getNome();
        assertEquals("", NOME_PADRAO, result);
    }

    /**
     * Test of toString method, of class ObjetoNomeado.
     */
    @Test
    public void testToString()
    {
        System.out.println("toString");
        /* Este método não precisa ser testado */
    }

    /**
     * Test of getRotulo method, of class ObjetoNomeado.
     */
    @Test
    public void testGetRotulo()
    {
        System.out.println("getRotulo");
        /* Este método não precisa ser testado */
    }    
}
