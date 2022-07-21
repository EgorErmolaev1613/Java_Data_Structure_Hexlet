package org.hexlet.JavaDataStructure.lesson5;

import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Iterator;

// Реализовать следующий метод <T> T[] toArray(T[] a)

public class ArrayCollection<T> implements Collection<T> {

    private T[] m = (T[]) new Object[1];

    private int size;

    @Override
    public final int size() {
        return this.size;
    }

    @Override
    public final boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public final boolean contains(final Object o) {
        for (int i = 0; i < size; i++) {
            if (m[i].equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public final Iterator<T> iterator() {
        return new ElementsIterator();
    }

    @Override
    public final Object[] toArray() {
        final T[] newM = (T[]) new Object[this.size()];
        System.arraycopy(m, 0, newM, 0, this.size());
        return newM;
    }

    @Override
    public final <T> T[] toArray(final T[] a) throws NullPointerException, ArrayStoreException { //Нужный метод
        // BEGIN (write your solution here)
        if (a.length < size) {
            return (T[]) Arrays.copyOf(m, size, a.getClass());
        }

        System.arraycopy(m, 0, a, 0, size);

        if (a.length > size) {
            a[size] = null;
        }
        return a;
        // END
    }

    @Override

    public final boolean add(final T t) {
        if (m.length == size) {
            final T[] oldM = m;
            m = (T[]) new Object[this.size() * 2];
            System.arraycopy(oldM, 0, m, 0, oldM.length);
        }
        m[size++] = t;
        return true;
    }

    @Override
    public final boolean remove(final Object o) {
        for (int i = 0; i < size(); i++) {
            if (m[i].equals(o)) {
                this.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public final boolean containsAll(final Collection<?> c) {
        for (final Object item : c) {
            if (!this.contains(item)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public final boolean addAll(final Collection<? extends T> c) {
        for (final T item : c) {
            add(item);
        }
        return true;
    }

    @Override
    public final boolean removeAll(final Collection<?> c) {
        for (final Object item : c) {
            remove(item);
        }
        return true;
    }

    @Override
    public final boolean retainAll(final Collection<?> c) {
        for (final Object item : this) {
            if (!c.contains(item)) {
                this.remove(item);
            }
        }
        return true;
    }

    @Override
    public final void clear() {
        m = (T[]) new Object[1];
        size = 0;
    }

    private void remove(final int index) {
        if (index != this.size() - 1) {
            System.arraycopy(m, index + 1, m, index, this.size() - index - 1);
        }
        size--;
    }

    private class ElementsIterator implements Iterator<T> {

        private int index;

        private int last = -1;

        @Override
        public boolean hasNext() {
            return ArrayCollection.this.size() > index;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            last = index;
            return ArrayCollection.this.m[index++];
        }

        @Override
        public void remove() {
            if (last == -1) {
                throw new IllegalStateException();
            }
            ArrayCollection.this.remove(last);
            index--;
            last = -1;
        }
    }
}

