package org.hexlet.JavaDataStructure.lesson12;
//Сегодня Вам предстоит самостоятельно реализовать интерфейс Set (на базе HashMap).
//        Как не трудно догадаться, имя реализации интерфейса Set -- HashSet
//
//        Напомним Вам, что интерфейс Set полностью дублирует методы интерфейса Collection,
//        с тем лишь исключением, что он добавляет новые ограничения на методы.
//
//        В некоторых методах, при одновременном итерировании и изменении коллекции,
//        Вы можете поймать ConcurrentModificationException. В данном случае, это не связано с применением многопоточности, а связано с атавизмами из самых первых версий java. Если интересно об этом почитать — вот статья. А в данном задании — просто внимательно следите за тем, по какой коллекции происходит итерация, и в какую коллекцию вносятся изменения. Или применяйте другие способы итерации, работайте с итераторами.
//
//        Ваша реализация должна соответствовать интерфейсу Set, исключая метода spliterator. Часть методов уже реализована в классе
import java.util.*;
import java.util.function.Consumer;

public class HashSet<T> implements Set<T> {

    static final Boolean EXIST = true;
    private static final Object PRESENT = new Object();
    private final Map<T, Boolean> elements = new HashMap<>();

    @Override
    public final int size() {
        // BEGIN (write your solution here)
        return elements.size();
        // END
    }

    @Override
    public final boolean isEmpty() {
        // BEGIN (write your solution here)
        return elements.size()==0;
        // END
    }

    @Override
    public final boolean contains(Object o) {
        // BEGIN (write your solution here)
        return elements.containsKey(o);
        // END
    }

    @Override
    public final Object[] toArray() {
        // BEGIN (write your solution here)
        return elements.entrySet().toArray();
        // END
    }

    @Override
    public final <T1> T1[] toArray(T1[] a) {
        // BEGIN (write your solution here)
        return (T1[]) elements.entrySet().toArray();
        // END
    }

    @Override
    public final boolean add(T t) {
        // BEGIN (write your solution here)
        return elements.put(t, EXIST)==null;
        // END
    }

    @Override
    public final boolean remove(Object o) {
        // BEGIN (write your solution here)
        return elements.remove(o) == EXIST;
        // END
    }

    @Override
    public final boolean containsAll(Collection<?> c) {
        // BEGIN (write your solution here)
        for (final Object item : c) {
            if (!this.contains(item)) {
                return false;
            }
        }
        return true;
        // END
    }

    @Override
    public final boolean addAll(Collection<? extends T> c) {
        // BEGIN (write your solution here)
        for (final T item : c) {
            this.elements.put(item, EXIST);
        }
        return true;
        // END
    }

    @Override
    public final boolean retainAll(Collection<?> c) {
        // BEGIN (write your solution here)
        boolean modified = false;
        Iterator<?> it = iterator();
        while (it.hasNext()) {
            if (!c.contains(it.next())) {
                it.remove();
                modified = true;
            }
        }
        return modified;
        // END
    }

    @Override
    public final boolean removeAll(Collection<?> c) {
        // BEGIN (write your solution here)
        for (final Object item : c) {
            this.elements.remove(item);
        }
        return true;
        // END
    }

    @Override
    public final Iterator<T> iterator() {
        return elements.keySet().iterator();
    }

    @Override
    public final void clear() {
        elements.clear();
    }

    @Override
    public final boolean equals(Object o) {
        if (o instanceof HashSet) {
            return elements.keySet().equals(o);
        }
        return false;
    }

    @Override
    public final int hashCode() {
        return elements.keySet().hashCode();
    }
}

