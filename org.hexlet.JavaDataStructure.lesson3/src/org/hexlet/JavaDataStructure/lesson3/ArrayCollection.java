package org.hexlet.JavaDataStructure.lesson3;
//Реализовать НЕ default-методы интерфейсов Collection и Iterator в классах ArrayCollection
//        и ElementsIterator соответственно. Вам необходимо реализовать интерфейс Collection на базе массива.
//        Внимательно ознакомьтесь с документацией по интерфейсу Collection. Документация Iterator.
//
//        Детальное описание задания
//        Из Collection необходимо реализовать следующие методы:
//        boolean add(E e)
//        boolean addAll(Collection<? extends E> c)
//        void clear()
//        boolean contains(Object o)
//        boolean containsAll(Collection<?> c)
//        boolean equals(Object o)
//        boolean removeAll(Collection<?> c)
//        boolean retainAll(Collection<?> c)
//        int size()
//        Object[] toArray()
//<T> T[] toArray(T[] a) - в случае, если переданный массив a имеет размер больший или равный
//        размеру текущей коллекции, то метод должен скопировать все элементы текущей коллекции в массив a.
//        И вернуть массив a. Если a меньше размера коллекции, то метод должен создать новый массив,
//        который имеет тот же тип что и массив a, но при этом имеет длину, равную длине коллекции. После,
//        метод должен скопировать все элементы коллекции в новый массив. И вернуть его.
//        Метод int hashCode() пока не реализовывайте. О хеше мы поговорим в конце курса.
//
//        Из Iterator необходимо реализовать следующие методы:
//        boolean hasNext()
//        E next()


import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayCollection<T> implements Collection<T> {

    private T[] array = (T[]) new Object[1];

    private int size;

    @Override
    public final int size() {
        // BEGIN (write your solution here)
        return this.size;
        // END
    }

    @Override
    public final boolean isEmpty() {
        // BEGIN (write your solution here)
        return this.size() == 0;
        // END
    }

    @Override
    public final boolean contains(final Object o) {
        // BEGIN (write your solution here)
        for (int i = 0; i < size; i++){
            if (array [i].equals(o))  return true;
        }
        return false;
        // END
    }

    @Override
    public final Iterator<T> iterator() {
        // BEGIN (write your solution here)
        return new ElementsIterator();
        // END
    }

    @Override
    public final Object[] toArray() {
        // BEGIN (write your solution here)
        final T [] newArray = (T[]) new Object[this.size()];
        System.arraycopy(array,0,newArray,0,this.size());
        // END
        return newArray;
    }

    @Override
    /*This method may prove to be too difficult.
    he test is not covered.*/
    public final <T1> T1[] toArray(T1[] a) {
        // BEGIN (write your solution here)
        return (T1 []) this.toArray();
        // END
    }

    @Override
    public final boolean add(final T t) {
        // BEGIN (write your solution here)
        if (array.length == size) {
            final T [] oldArray = array;
            array = (T []) new Object [this.size()*2];
            System.arraycopy(oldArray,0,array,0,oldArray.length);
        }
        array[size] = t;
        size++;
        return true;
        // END
    }

    @Override
    public final boolean remove(final Object o) {
        // BEGIN (write your solution here)
        for (int i =0; i < size();i++){
            if (array[i].equals(o)) {
                if (i != this.size()-1)
                    System.arraycopy(array,i +1,array,i,this.size()-i-1);
                size--;
                return true;
            }
        }
        return false;
        // END
    }

    @Override
    public final boolean containsAll(final Collection<?> c) {
        // BEGIN (write your solution here)
        for (final Object item : c){
            if (!this.contains(item)) return false;
        }
        return true;
        // END
    }

    @Override
    public final boolean addAll(final Collection<? extends T> c) {
        // BEGIN (write your solution here)
        for (final T item : c) {
            add(item);
        }
        return true;
        // END
    }

    @Override
    public final boolean removeAll(final Collection<?> c) {
        // BEGIN (write your solution here)
        for (final Object item : c){
            remove(item);
        }   return true;
        // END
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        // BEGIN (write your solution here)
        ArrayCollection ac = new ArrayCollection();
        for (Object item : this) {
            System.out.println(item);
            if (!c.contains(item)) ac.add(item);
        }
        this.removeAll(ac);
        return true;
        // END
    }
    @Override
    public final void clear() {
        // BEGIN (write your solution here)
        array = (T []) new Object [1];
        size = 0;
        // END
    }

    private class ElementsIterator implements Iterator<T> {

        private int size;

        @Override
        public boolean hasNext() {

            return ArrayCollection.this.size() > size;
        }

        @Override
        public T next() {
            if (this.hasNext() == false) {
                throw new NoSuchElementException();
            }
            return ArrayCollection.this.array[size++];
        }

    }

}

