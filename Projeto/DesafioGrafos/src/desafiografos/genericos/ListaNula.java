/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafiografos.genericos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Consumer;

/**
 * Implementa uma instância completamente vazia da interface List&lt;T&gt;
 * com o propósito de retornar um objeto estático sempre que um método
 * precisar devolver uma lista sem nenhum elemento.
 * 
 * Isso poupa processamento e memória que seriam desperdiçados com
 * a criação de múltiplas instâncias de listas concretas sem nenhum item
 * 
 * Recomenda-se sua utilização de acordo com o padrão de projeto Singletron
 * 
 * @author Henrique
 * @param <T> 
 */
public class ListaNula<T> implements List<T>
{
    private final T[] elementos = (T[])new Object[0];
    private final Iterator<T> iterator = new Iterator<T>()
        {
            @Override
            public boolean hasNext()
            {
                return false;
            }

            @Override
            public T next()
            {
                return null;
            }
        };
    private final ListIterator<T> listIterator = new ListIterator<T>() {
        @Override
        public boolean hasNext()
        {
            return false;
        }

        @Override
        public T next()
        {
            return null;
        }

        @Override
        public boolean hasPrevious()
        {
            return false;
        }

        @Override
        public T previous()
        {
            throw new UnsupportedOperationException("Operação não suportada");
        }

        @Override
        public int nextIndex()
        {
            throw new UnsupportedOperationException("Operação não suportada");
        }

        @Override
        public int previousIndex()
        {
            throw new UnsupportedOperationException("Operação não suportada"); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void remove()
        {
            throw new UnsupportedOperationException("Operação não suportada"); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void set(T e)
        {
            throw new UnsupportedOperationException("Operação não suportada"); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void add(T e)
        {
            throw new UnsupportedOperationException("Operação não suportada"); //To change body of generated methods, choose Tools | Templates.
        }
    };
            
    
    public ListaNula()
    {
    }
    
    @Override
    public int size()
    {
        ArrayList<T> result = new ArrayList<>();
        result.toArray();
        return 0;
    }

    @Override
    public boolean isEmpty()
    {
        return true;
    }

    @Override
    public boolean contains(Object o)
    {
        return false;
    }

    @Override
    public Iterator<T> iterator()
    {
        return this.iterator;
    }

    @Override
    public Object[] toArray()
    {
        return this.elementos;
    }

    @Override
    public <T> T[] toArray(T[] a)
    {
        return (T[]) this.elementos;
    }

    @Override
    public boolean add(T e)
    {
        throw new UnsupportedOperationException("Operação não suportada."); 
    }

    @Override
    public boolean remove(Object o)
    {
        throw new UnsupportedOperationException("Operação não suportada."); 
    }

    @Override
    public boolean containsAll(Collection<?> c)
    {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c)
    {
        throw new UnsupportedOperationException("Operação não suportada."); 
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c)
    {
        throw new UnsupportedOperationException("Operação não suportada."); 
    }

    @Override
    public boolean removeAll(Collection<?> c)
    {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c)
    {
        return false;
    }

    @Override
    public void clear()
    {

    }

    @Override
    public T get(int index)
    {
        throw new UnsupportedOperationException("Operação não suportada."); 
    }

    @Override
    public T set(int index, T element)
    {
        throw new UnsupportedOperationException("Operação não suportada."); 
    }

    @Override
    public void add(int index, T element)
    {
        throw new UnsupportedOperationException("Operação não suportada."); 
    }

    @Override
    public T remove(int index)
    {
        throw new UnsupportedOperationException("Operação não suportada."); 
    }

    @Override
    public int indexOf(Object o)
    {
        return -1; 
    }

    @Override
    public int lastIndexOf(Object o)
    {
        return -1;
    }

    @Override
    public ListIterator<T> listIterator()
    {
        return this.listIterator;
    }

    @Override
    public ListIterator<T> listIterator(int index)
    {
        return this.listIterator;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex)
    {
        return this; 
    }
    
}
