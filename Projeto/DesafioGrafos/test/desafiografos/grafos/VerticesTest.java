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
public class VerticesTest
{
    Vertices colecao;
    
    public VerticesTest()
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
        this.colecao = new Vertices();
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of criarOuObter method, of class Vertices.
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
            /* primeiro vamos testar a criação de um item novo*/
            Vertice expResult = new Vertice("A");
            Vertice result = this.colecao.criarOuObter("A");
            assertEquals(String.format("criarOuObter(\"A\")=%s; Esperado=[%s]",result, expResult), expResult,result);
            assertFalse("Foi adicionado apenas um item, porém a coleção contém mais de um",this.colecao.contagem()>1);
            assertFalse("Foi adicionado um item, porém a coleção contém menos de um",this.colecao.contagem()<1);
            /* agora testamos a obtenção de um item que já existe */
            result = this.colecao.criarOuObter("A");
            assertEquals(String.format("criarOuObter(\"A\")=%s; Esperado=[%s]",result, expResult), expResult,result);
            /* Se o item já existia, significa que não pode ter sido criado um item novo */
            assertFalse("Foi adicionado um item que já existia, porém um item duplicado foi criado",this.colecao.contagem()>1);
        }
        catch (InvalidNameException ex)
        {
            fail("Falha na execução: " + ex.toString());
        }
    }
    
}
