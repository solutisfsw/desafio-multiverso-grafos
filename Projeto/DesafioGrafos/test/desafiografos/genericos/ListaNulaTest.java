/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafiografos.genericos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
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
public class ListaNulaTest
{
    ListaNula lista;
    
    public ListaNulaTest()
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
        lista = new ListaNula();
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of size method, of class ListaNula.
     */
    @Test
    public void testSize()
    {
        System.out.println("size");
        try
        {
            this.lista.add(0);
        }catch(Exception ex){}
        assertEquals("O método size() da lista nula deveria retornar sempre zero após a tentativa de adição de elementos", 0, this.lista.size());

        try
        {
            this.lista.remove(0);
        }catch(Exception ex){}
        assertEquals("O método size() da lista nula deveria retornar sempre zero após a tentativa de remoção de elementos", 0, this.lista.size());
    }

    /**
     * Test of isEmpty method, of class ListaNula.
     */
    @Test
    public void testIsEmpty()
    {
        System.out.println("isEmpty");
        try
        {
            this.lista.add(0);
        }catch(Exception ex){}
        assertTrue("O método isEmpty() da lista nula deveria retornar sempre true após a tentativa de adição de elementos", this.lista.isEmpty());

        try
        {
            this.lista.remove(0);
        }catch(Exception ex){}
        assertTrue("O método isEmpty() da lista nula deveria retornar sempre true após a tentativa de remoção de elementos", this.lista.isEmpty());
    }

    /**
     * Test of contains method, of class ListaNula.
     */
    @Test
    public void testContains()
    {
        System.out.println("contains");
        try
        {
            this.lista.add(0);
        }catch(Exception ex){}
        assertFalse("O método contains() da lista nula deveria retornar sempre false após a tentativa de adição de elementos", this.lista.contains(0));
    }

    /**
     * Test of iterator method, of class ListaNula.
     */
    @Test
    public void testIterator()
    {
        System.out.println("iterator");
        try
        {
            this.lista.add(0);
        }catch(Exception ex){}
        Iterator it = this.lista.iterator();
        assertFalse("O iterator da lista nula deve não deve conter nenhum elemento, mesmo após a tentativa de adição de elementos", it.hasNext());
    }

    /**
     * Test of toArray method, of class ListaNula.
     */
    @Test
    public void testToArray_0args()
    {
        System.out.println("toArray");
        try
        {
            this.lista.add(0);
        }catch(Exception ex){}
        Object[] result = this.lista.toArray();
        assertEquals("O método toArray() da lista nula deve sempre retornar um array vazio", 0, result.length);
    }

    /**
     * Test of toArray method, of class ListaNula.
     */
    @Test
    public void testToArray_GenericType()
    {
        System.out.println("toArray");
        try
        {
            this.lista.add(0);
        }catch(Exception ex){}
        Object[] result = this.lista.toArray();
        assertEquals("O método toArray() da lista nula deve sempre retornar um array vazio", 0, result.length);
    }

    /**
     * Test of add method, of class ListaNula.
     */
    @Test
    public void testAdd_GenericType()
    {
        System.out.println("add");
        boolean sucesso;
        try
        {
            this.lista.add(0);
            /* Deveria ter disparado uma exceção */
            sucesso=false;
        }
        catch(Exception ex)
        {
            /* Foi disparada uma exceção, conforme esperado */
            sucesso=true;
        }
        assertTrue("A lista nula não deve permitir a adição de novos elementos",sucesso);
    }

    /**
     * Test of remove method, of class ListaNula.
     */
    @Test
    public void testRemove_Object()
    {
        System.out.println("remove");
        boolean sucesso;
        try
        {
            this.lista.remove(0);
            /* Deveria ter disparado uma exceção */
            sucesso=false;
        }
        catch(Exception ex)
        {
            /* Foi disparada uma exceção, conforme esperado */
            sucesso=true;
        }
        assertTrue("A lista nula não deve permitir a remoção elementos",sucesso);
    }

    /**
     * Test of containsAll method, of class ListaNula.
     */
    @Test
    public void testContainsAll()
    {
        System.out.println("containsAll");
        try
        {
            this.lista.add(0);
        }catch(Exception ex){}
        List listaTeste = new ArrayList();
        listaTeste.add(0);
        assertFalse("O método containsAll() da lista nula deve sempre retornar false, mesmo após a tentativa de adicionar itens", this.lista.containsAll(listaTeste));
    }

    /**
     * Test of addAll method, of class ListaNula.
     */
    @Test
    public void testAddAll_Collection()
    {
        System.out.println("addAll");
        List listaTeste = new ArrayList();
        listaTeste.add(0);
        boolean sucesso;
        try
        {
            this.lista.addAll(listaTeste);
            /* Deveria ter disparado uma exceção */
            sucesso=false;
        }
        catch(Exception ex)
        {
            /* Foi disparada uma exceção, conforme esperado */
            sucesso=true;
        }
        assertTrue("A lista nula não deve permitir a adição de novos elementos",sucesso);
    }

    /**
     * Test of addAll method, of class ListaNula.
     */
    @Test
    public void testAddAll_int_Collection()
    {
        System.out.println("addAll");
        List<Integer> listaTeste = new ArrayList();
        listaTeste.add(0);
        boolean sucesso;
        try
        {
            this.lista.addAll(listaTeste);
            /* Deveria ter disparado uma exceção */
            sucesso=false;
        }
        catch(Exception ex)
        {
            /* Foi disparada uma exceção, conforme esperado */
            sucesso=true;
        }
        assertTrue("A lista nula não deve permitir a adição de novos elementos",sucesso);
    }

    /**
     * Test of removeAll method, of class ListaNula.
     */
    @Test
    public void testRemoveAll()
    {
        System.out.println("removeAll");
        List listaTeste = new ArrayList();
        listaTeste.add(0);
        /* O resultado deve ser sempre False */
        boolean result = this.lista.removeAll(listaTeste);
        assertFalse("O método removeAll()deve reotrnar sempre false",result);
    }

    /**
     * Test of retainAll method, of class ListaNula.
     */
    @Test
    public void testRetainAll()
    {
        System.out.println("retainAll");
        List listaTeste = new ArrayList();
        listaTeste.add(0);
        /* O resultado deve ser sempre False */
        boolean result = this.lista.retainAll(listaTeste);
        assertFalse("O método retainAll()deve reotrnar sempre false",result);
    }

    /**
     * Test of clear method, of class ListaNula.
     */
    @Test
    public void testClear()
    {
        System.out.println("clear");
        /* O método clear não precisa ser testado em uma lista sem elementos */
    }

    /**
     * Test of get method, of class ListaNula.
     */
    @Test
    public void testGet()
    {
        System.out.println("get");
        boolean sucesso;
        try
        {
            this.lista.get(0);
            /* Deveria ter disparado uma exceção */
            sucesso=false;
        }
        catch(Exception ex)
        {
            /* Foi disparada uma exceção, conforme esperado */
            sucesso=true;
        }
        assertTrue("O método get() não deve ser suportado pela lista nula",sucesso);
    }

    /**
     * Test of set method, of class ListaNula.
     */
    @Test
    public void testSet()
    {
        System.out.println("set");
        boolean sucesso;
        try
        {
            this.lista.set(0,0);
            /* Deveria ter disparado uma exceção */
            sucesso=false;
        }
        catch(Exception ex)
        {
            /* Foi disparada uma exceção, conforme esperado */
            sucesso=true;
        }
        assertTrue("O método set() não deve ser suportado pela lista nula",sucesso);
    }

    /**
     * Test of add method, of class ListaNula.
     */
    @Test
    public void testAdd_int_GenericType()
    {
        System.out.println("add");
        boolean sucesso;
        try
        {
            this.lista.add(0);
            /* Deveria ter disparado uma exceção */
            sucesso=false;
        }
        catch(Exception ex)
        {
            /* Foi disparada uma exceção, conforme esperado */
            sucesso=true;
        }
        assertTrue("O método add() não deve ser suportado pela lista nula",sucesso);
    }

    /**
     * Test of remove method, of class ListaNula.
     */
    @Test
    public void testRemove_int()
    {
        System.out.println("remove");
        boolean sucesso;
        try
        {
            this.lista.remove(0);
            /* Deveria ter disparado uma exceção */
            sucesso=false;
        }
        catch(UnsupportedOperationException ex)
        {
            /* Foi disparada uma exceção, conforme esperado */
            sucesso=true;
        }
        catch(Exception ex)
        {
            /* Foi disparada uma exceção, porém não foi o que era esperado */
            sucesso=false;
        }
        assertTrue("O método remove() não deve ser suportado pela lista nula",sucesso);
    }

    /**
     * Test of indexOf method, of class ListaNula.
     */
    @Test
    public void testIndexOf()
    {
        System.out.println("indexOf");
        int result = this.lista.indexOf(0);
        assertEquals("O método indexOf() da lista nula deve retornar sempre -1",-1,result);
    }

    /**
     * Test of lastIndexOf method, of class ListaNula.
     */
    @Test
    public void testLastIndexOf()
    {
        System.out.println("lastIndexOf");
        int result = this.lista.lastIndexOf(0);
        assertEquals("O método lastIndexOf() da lista nula deve retornar sempre -1",-1,result);
    }

    /**
     * Test of listIterator method, of class ListaNula.
     */
    @Test
    public void testListIterator_0args()
    {
        System.out.println("listIterator");
        try
        {
            this.lista.add(0);
        }catch(Exception ex){}
        ListIterator it = this.lista.listIterator();
        assertFalse("O listIterator da lista nula deve não deve conter nenhum elemento, mesmo após a tentativa de adição de elementos", it.hasNext());
    }

    /**
     * Test of listIterator method, of class ListaNula.
     */
    @Test
    public void testListIterator_int()
    {
        System.out.println("listIterator");
        try
        {
            this.lista.add(0);
        }catch(Exception ex){}
        ListIterator it = this.lista.listIterator(0);
        assertFalse("O listIterator da lista nula deve não deve conter nenhum elemento, mesmo após a tentativa de adição de elementos", it.hasNext());
    }

    /**
     * Test of subList method, of class ListaNula.
     */
    @Test
    public void testSubList()
    {
        System.out.println("subList");
        ListaNula subLista = null;
        try
        {
            subLista = (ListaNula)this.lista.subList(0,100);            
        }catch (Exception ex){}
        assertTrue("O método subList() da lista nula deve retornar a própria lista", subLista == this.lista);
    }
    
}
