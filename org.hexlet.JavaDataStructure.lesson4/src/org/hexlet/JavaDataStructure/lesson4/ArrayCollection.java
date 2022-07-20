package org.hexlet.JavaDataStructure.lesson4;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

//В этом практическом задании Вам необходимо реализовать интерфейс Iterator.
//        Внимательно ознакомьтесь с документацией по интерфейсу Iterator.
//        Подобное задание в упрощенном виде уже предлагалось к выполнению ранее,
//        однако тогда не было требования реализовать метод удаления и не проверялись исключения.
//        Сегодня Вам предстоит реализовать полноценный итератор на 100% сосуществующий документации.

//        Реализовать необходимо методы (в точности так, как описано в документации):
//
//        boolean hasNext()
//        E next()
//        void remove()
//        Убедитесь, что Вы правильно поняли спецификацию каждого метода.
//        Нарисуйте себе схему. Эти методы тесно связаны.
//
//        Корректность Вашей реализации будет проверена Unit тестами,
//        которые Вы можете найти в классе ArrayCollectionTest.
//
//        Имя класса, в котором Вам необходимо написать реализацию: ArrayCollection
//
//        Уделите особое внимание исключениям, которые бросают методы, и тому как методы зависят друг от друга.
//
//        Дополнительная информация.
//
//        Метод forEachRemaining(Consumer<? super E> action) уже определен и наполнен кодом в интерфейсе.
//
//        Поэтому он имеет ключевое слово default.
//        Но никто не запрещает его переопределить в конкретной реализации интерфейса,
//        если в этом есть необходимость.
public final class ArrayCollection<T> implements Collection<T> {

    private T[] baseArray = (T[]) new Object[1];

    private int size;

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public boolean contains(final Object o) {
        for (int i = 0; i < size; i++) {
            if (baseArray[i].equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new ElementsIterator();
    }

    @Override
    public Object[] toArray() {
        final T[] newM = (T[]) new Object[this.size()];
        System.arraycopy(baseArray, 0, newM, 0, this.size());
        return newM;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return (T1[]) this.toArray();
    }

    @Override
    public boolean add(final T t) {
        if (baseArray.length == size) {
            final T[] oldM = baseArray;
            baseArray = (T[]) new Object[this.size() * 2];
            System.arraycopy(oldM, 0, baseArray, 0, oldM.length);
        }
        baseArray[size++] = t;
        return true;
    }

    @Override
    public boolean remove(final Object o) {
        for (int i = 0; i < size(); i++) {
            if (baseArray[i].equals(o)) {
                this.remove(i);
                return true;
            }
        }
        return false;
    }

    private void remove(final int index) {
        if (index != this.size() - 1) {
            System.arraycopy(baseArray, index + 1, baseArray, index, this.size() - index - 1);
        }
        if (this.size() != 0) {
            size--;
        }
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        for (final Object item : c) {
            if (!this.contains(item)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(final Collection<? extends T> c) {
        for (final T item : c) {
            add(item);
        }
        return true;
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        for (final Object item : c) {
            remove(item);
        }
        return true;
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        for (final T item : this) {
            if (!c.contains(item)) {
                this.remove(item);
            }
        }
        return true;
    }

    @Override
    public void clear() {
        baseArray = (T[]) new Object[1];
        size = 0;
    }

    private class ElementsIterator implements Iterator<T> {
        private int index = 0;
        boolean canRemove = false;
        @Override
        public boolean hasNext() {
            return ArrayCollection.this.size() > index;
        }

        @Override
        public T next() {
            if (this.hasNext() == false){
                throw new NoSuchElementException();
            }
            canRemove = true;
            return ArrayCollection.this.baseArray[index++];
        }

        @Override
        public void remove() throws UnsupportedOperationException,IllegalStateException {
            if (canRemove == false){
                throw new IllegalStateException();
            }

            ArrayCollection.this.remove(--index);
            canRemove = false;
        }


    }
}