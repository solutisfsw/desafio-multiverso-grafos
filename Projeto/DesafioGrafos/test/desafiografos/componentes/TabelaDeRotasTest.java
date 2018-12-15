/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafiografos.componentes;

import desafiografos.grafos.Caminho;
import desafiografos.grafos.Grafo;
import desafiografos.grafos.Grafo.OpcoesDeBusca;
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
public class TabelaDeRotasTest
{
    private Grafo grafo;
    private TabelaDeRotas tabela;
    private boolean grafoCarregado;
    private boolean tabelaCarregada;
    private List<Caminho> caminhos;
    private boolean caminhosCarregados;
    private String[] colunas = {"Rota","Distância","Paradas","Trajeto"};
    
    public TabelaDeRotasTest()
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
            this.grafo = new Grafo();
            /**
             * Para esse teste, será criado um grafo completo (todos os vértices
             * conectados a todos os outros) contendo 5 vértices (A a E)
             */
            for (char v1 = 'A';v1<='E';v1++)
            {
                for (char v2 = 'A';v2<='E';v2++)
                {
                    grafo.criarOuObterAresta(v1+"", v2+"", 10);
                }
            }
            this.grafoCarregado = true;
        }
        catch (Exception ex)
        {
            this.grafoCarregado = false;
        }
        if (this.grafoCarregado)
        {
            try
            {
                this.caminhos = this.grafo.getCaminhos("A", "E", null, 0, 0, OpcoesDeBusca.TodasAsRotas);
                this.caminhosCarregados = true;
            }
            catch(Exception ex)
            {
            this.caminhosCarregados = false;
            }
        }
        else
        {
            this.caminhosCarregados = false;
        }
        if (this.caminhosCarregados)
        {
            try
            {
                this.tabela = new TabelaDeRotas(this.caminhos);
                this.tabelaCarregada = true;
            }
            catch(Exception ex)
            {
            this.tabelaCarregada = false;
            }
        }
        else
        {
            this.tabelaCarregada = false;
        }
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of getRowCount method, of class TabelaDeRotas.
     */
    @Test
    public void testGetRowCount()
    {
        System.out.println("getRowCount");
        assertTrue("O grafo não foi carregado", this.grafoCarregado);
        assertTrue("As rotas não foram carregadas", this.caminhosCarregados);
        assertTrue("A tabela não foi carregada", this.tabelaCarregada);
        int result = this.tabela.getRowCount();
        assertTrue("O métdo getRowCount() deveria retornar um número maior que 0", result>0);
    }

    /**
     * Test of getColumnCount method, of class TabelaDeRotas.
     */
    @Test
    public void testGetColumnCount()
    {
        System.out.println("getColumnCount");
        assertTrue("O grafo não foi carregado", this.grafoCarregado);
        assertTrue("As rotas não foram carregadas", this.caminhosCarregados);
        assertTrue("A tabela não foi carregada", this.tabelaCarregada);
        int expResult = 4;
        int result = this.tabela.getColumnCount();
        assertEquals(String.format("O método getColumnCount() retornou %d colunas, porém %d era o esperado", result,expResult), expResult, result);
    }

    /**
     * Test of getValueAt method, of class TabelaDeRotas.
     */
    @Test
    public void testGetValueAt()
    {
        System.out.println("getValueAt");
        assertTrue("O grafo não foi carregado", this.grafoCarregado);
        assertTrue("As rotas não foram carregadas", this.caminhosCarregados);
        assertTrue("A tabela não foi carregada", this.tabelaCarregada);
        for (int rowIndex = 0;rowIndex<this.tabela.getRowCount();rowIndex++)
        {
            Caminho caminho = this.tabela.getValueAt(rowIndex);
            assertNotNull(String.format("O método getValueAt(%d) retornou um objeto nulo", rowIndex),caminho);
            for(int columnIndex = 0;columnIndex<this.tabela.getColumnCount();columnIndex++)
            {
                Object result = this.tabela.getValueAt(rowIndex, columnIndex);
                assertNotNull(String.format("O método getValueAt(%d, %d) retornou um valor nulo",rowIndex, columnIndex), result);
            }
        }
        
    }

    /**
     * Test of getDados method, of class TabelaDeRotas.
     */
    @Test
    public void testGetDados()
    {
        System.out.println("getDados");
        assertTrue("O grafo não foi carregado", this.grafoCarregado);
        assertTrue("As rotas não foram carregadas", this.caminhosCarregados);
        assertTrue("A tabela não foi carregada", this.tabelaCarregada);
        Object result = this.tabela.getDados();
        assertEquals(String.format("O método getDados() está retornando um objeto diferente daquele que foi atribuído na inicialização.",this.caminhos, result), this.caminhos, result);
    }

    /**
     * Test of setDados method, of class TabelaDeRotas.
     */
    @Test
    public void testSetDados()
    {
        System.out.println("setDados");
        this.tabela.setDados(this.caminhos);
        Object result = this.tabela.getDados();
        assertEquals(String.format("O método getDados() está retornando um objeto diferente daquele que foi atribuído pelo método setDados().",this.caminhos, result), this.caminhos, result);
    }

    /**
     * Test of getColunas method, of class TabelaDeRotas.
     */
    @Test
    public void testGetColunas()
    {
        System.out.println("getColunas");
        String[] result = TabelaDeRotas.getColunas();
        assertArrayEquals("O conjunto de colunas não corresponde ao esperado",this.colunas, result);
    }

    /**
     * Test of getColumnName method, of class TabelaDeRotas.
     */
    @Test
    public void testGetColumnName()
    {
        System.out.println("getColumnName");
        boolean expResult = this.colunas.length == this.tabela.getColumnCount();
        
        for(int column=0;expResult && column<this.colunas.length;column++)
        {
            expResult = expResult & this.colunas[column].equals(this.tabela.getColumnName(column));
        }
        assertTrue("Os nomes das colunas não correspondem ao esperado", expResult);
    }
}
