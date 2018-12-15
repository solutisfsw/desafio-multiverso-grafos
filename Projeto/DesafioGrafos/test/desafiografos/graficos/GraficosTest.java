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
public class GraficosTest
{
    private static final Ponto2D[] PONDOS_DE_ORIGEM =
            {
                new Ponto2D(0,0),
                new Ponto2D(0,0),
                new Ponto2D(0,100),
                new Ponto2D(0,100),
                new Ponto2D(100,0),
                new Ponto2D(100,0),
                new Ponto2D(100,100),
                new Ponto2D(100,100)
            };
    private static final Ponto2D[] PONTOS_DE_DESTINO =
            {
                new Ponto2D(100,0),
                new Ponto2D(100,100),
                new Ponto2D(100,0),
                new Ponto2D(100,100),
                new Ponto2D(0,0),
                new Ponto2D(0,100),
                new Ponto2D(0,0),
                new Ponto2D(0,100)
            };
    private static final Ponto2D[] PONTOS_MEDIOS =
            {
                new Ponto2D(50,0),
                new Ponto2D(50,50),
                new Ponto2D(50,50),
                new Ponto2D(50,100),
                new Ponto2D(50,0),
                new Ponto2D(50,50),
                new Ponto2D(50,50),
                new Ponto2D(50,100)
            };
    private static final Ponto2D[] VETORES_TANGENTES =
            {
                new Ponto2D(0,-1),
                new Ponto2D(0.71,-0.71),
                new Ponto2D(-0.71,-0.71),
                new Ponto2D(0,-1),
                new Ponto2D(0,1),
                new Ponto2D(0.71,0.71),
                new Ponto2D(-0.71,0.71),
                new Ponto2D(0,1)
            };
    private static final Ponto2D[] VETORES_UNITARIOS =
            {
                new Ponto2D(1,0),
                new Ponto2D(0.71,0.71),
                new Ponto2D(0.71,-0.71),
                new Ponto2D(1,0),
                new Ponto2D(-1,0),
                new Ponto2D(-0.71,0.71),
                new Ponto2D(-0.71,-0.71),
                new Ponto2D(-1,0)
            };
    private static final Ponto2D[] VETORES_SIMPLES =
            {
                new Ponto2D(100,0),
                new Ponto2D(100,100),
                new Ponto2D(100,-100),
                new Ponto2D(100,0),
                new Ponto2D(-100,0),
                new Ponto2D(-100,100),
                new Ponto2D(-100,-100),
                new Ponto2D(-100,0)
            };
    private static final Ponto2D[] PONTOS_TANGENTES =
            {
                new Ponto2D(50,0),
                new Ponto2D(50,50),
                new Ponto2D(50,50),
                new Ponto2D(50,100),
                new Ponto2D(50,0),
                new Ponto2D(50,50),
                new Ponto2D(50,50),
                new Ponto2D(50,100)
            };
    
    public GraficosTest()
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
     * Test of getCentro method, of class Graficos.
     */
    @Test
    public void testObterCentro()
    {
        /**
         * O método getCentro deve retornar o ponto médio entre dois pontos fornecidos.
         */
        System.out.println("obterCentro");
        for (int pos=0;pos<8;pos++)
        {
            Ponto2D origem = PONDOS_DE_ORIGEM[pos];
            Ponto2D destino = PONTOS_DE_DESTINO[pos];
            Ponto2D esperado = PONTOS_MEDIOS[pos];
            Ponto2D result = Graficos.getCentro(origem, destino);
            assertEquals(String.format("obterCentro([%s],[%s])=[%s]; Esperado=[%s]",origem, destino,result,esperado),esperado, result);
        }
    }

    /**
     * Test of getTangente method, of class Graficos.
     */
    @Test
    public void testObterTangente()
    {
        /**
         * O ponto tangente é um ponto perpendicular ao centrp do segmento de reta
         * formado por dois pontos fornecidos
         */
        System.out.println("obterTangente");
        for (int pos=0;pos<8;pos++)
        {
            Ponto2D origem = PONDOS_DE_ORIGEM[pos];
            Ponto2D destino = PONTOS_DE_DESTINO[pos];
            Ponto2D esperado = PONTOS_TANGENTES[pos];
            Ponto2D result = Graficos.getTangente(origem, destino, 0);
            assertEquals(String.format("obterCentro([%s],[%s],1)=[%s]; Esperado=[%s]",origem, destino,result,esperado),esperado, result);
        }
    }

    /**
     * Test of getVetorTangente method, of class Graficos.
     */
    @Test
    public void testObterVetorTangente()
    {
        /**
         * O vetor tangente é um vetor perpendicualar à reta que cruza os dois
         * pontos fornecidos, com centro na porigem do sistema de coordenadas
         * e módulo igual a uma constante especificada
         */
        System.out.println("obterVetorTangente");
        for (int pos=0;pos<8;pos++)
        {
            Ponto2D origem = PONDOS_DE_ORIGEM[pos];
            Ponto2D destino = PONTOS_DE_DESTINO[pos];
            Ponto2D esperado = VETORES_TANGENTES[pos];
            Ponto2D result = Graficos.getVetorTangente(origem, destino, 1);
            assertEquals(String.format("obterVetorTangente([%s],[%s],1)=[%s]; Esperado=[%s]",origem, destino,result,esperado),esperado, result);
        }
    }

    /**
     * Test of getVetor method, of class Graficos.
     */
    @Test
    public void testObterVetor_Ponto2D_Ponto2D()
    {
        /**
         * O método getVetor(), constrói um vetor a partir de dois pontos
         * fornecidos. O modulo do vetor terá o módulo determinado pela
         * diferença entre esses pontos.
         */
        System.out.println("obterVetor");
        for (int pos=0;pos<8;pos++)
        {
            Ponto2D origem = PONDOS_DE_ORIGEM[pos];
            Ponto2D destino = PONTOS_DE_DESTINO[pos];
            Ponto2D esperado = VETORES_SIMPLES[pos];
            Ponto2D result = Graficos.getVetor(origem, destino);
            assertEquals(String.format("obterVetor([%s],[%s])=[%s]; Esperado=[%s]",origem, destino,result,esperado),esperado, result);
        }
    }

    /**
     * Test of getVetor method, of class Graficos.
     */
    @Test
    public void testObterVetor_3args()
    {
        /**
         * O método getVetor(), constrói um vetor a partir de dois pontos
         * fornecidos. O modulo do vetor terá o módulo determinado pela
         * diferença entre esses pontos multiplicado pela constante fornecida.
         */
        System.out.println("obterVetor");
        for (int pos=0;pos<8;pos++)
        {
            Ponto2D origem = PONDOS_DE_ORIGEM[pos];
            Ponto2D destino = PONTOS_DE_DESTINO[pos];
            Ponto2D esperado = VETORES_UNITARIOS[pos];
            Ponto2D result = Graficos.getVetor(origem, destino, 1);
            assertEquals(String.format("obterVetor([%s],[%s],1)=[%s]; Esperado=[%s]",origem, destino,result,esperado),esperado, result);
        }
    }

    /**
     * Test of desenharArco method, of class Graficos.
     */
    @Test
    public void testdesenharArco()
    {
        System.out.println("desenharArco");
        /**
         * Esse método possui efeito puramente gráfio e requer um teste visual.
         * O teste algorítimico é possível, porém foge muito ao escopo
         * do projeto.
         */
    }

    /**
     * Test of desenharVertice method, of class Graficos.
     */
    @Test
    public void testdesenharVertice()
    {
        System.out.println("desenharVertice");
        /**
         * Esse método possui efeito puramente gráfio e requer um teste visual.
         * O teste algorítimico é possível, porém foge muito ao escopo
         * do projeto.
         */
    }
    
}
