package org.hexlet.JavaDataStructure.lesson10;

//В Вашем распоряжении есть список пар,
//        каждая пара представлена в виде объекта типа ArrayBasedMap.Pair
//
//        Ваша задача реализовать следующие методы интерфейса Map:
//
//        size()
//        isEmpty()
//        containsKey(Object key)
//        containsValue(Object value)
//        get(Object key) {
//        put(K key, V value)
//        remove(Object key)
//        clear()
//        values()
//        Некоторые методы уже реализованы за Вас, например:
//
//        entrySet() - мы еще не рассматривали тему Set
//        keySet() - по той же причине
//        putAll(Map<? extends K, ? extends V> m) - требует немного магии Generics
//        О том, как будем тестировать
//
//        Тестирование Вашего ответа будет проводиться специальным тестом,
//        разработанным в Google. Дело в том, что у них есть своя библиотека,
//        которая реализует основные коллекции
//        так что не только Вы занимаетесь полной реализацией всех популярных коллекций с нуля;) ).
//        Для тестирования того, что коллекция работает корректно, у них существуют Unit тесты,
//        один из которых с небольшими изменениями и будет тестировать корректность Вашей реализации.


import java.util.*;

public class ArrayBasedMap<K, V> implements Map<K, V>  {

    private final List<Pair> keyAndValues = new ArrayList<>();

    @Override
    public final int size() {
        // BEGIN (write your solution here)
        return keyAndValues.size();
        // END
    }

    @Override
    public final boolean isEmpty() {
        // BEGIN (write your solution here)
        return keyAndValues.size() == 0;
        // END
    }

    @Override
    public final boolean containsKey(Object key) {
        // BEGIN (write your solution here)
        for (Pair p : keyAndValues) {
            if (p.getKey().equals(key)) {
                return true;
            }
        }
        return false;
        // END

    }
    @Override
    public final boolean containsValue(Object value) {
        // BEGIN (write your solution here)
        for (int i =0; i < keyAndValues.size();i++) {
            if (keyAndValues.get(i).getValue().equals(value)){
                return true;
            }
        }
        // END
        return false;
    }

    @Override
    public final V get(Object key) {
        // BEGIN (write your solution here)
        if (key == null){
            throw new NullPointerException();
        }
        for (int i =0; i < keyAndValues.size();i++) {
            if (keyAndValues.get(i).getKey().equals(key)){
                return keyAndValues.get(i).getValue();
            }
        }
        // END
        return null;
    }

    @Override
    public final V put(K key, V value) {
        // BEGIN (write your solution here)
        V result = value;
        if (!containsKey(key)) {
            keyAndValues.add(new Pair(key, value));
            return null;
        }
        for (Pair p : keyAndValues) {
            if (p.getKey().equals(key)) {
                result = p.getValue();
                p.setValue(value);
                break;
            }
        }
        return result;
        // END
    }

    public int getIndex (K key){
        for (int i =0; i < keyAndValues.size();i++) {
            if (keyAndValues.get(i).getKey().equals(key)) {
                return i;
            }

        }
        return 0;
    }

    @Override
    public final V remove(Object key) {
        // BEGIN (write your solution here)
        if (containsKey(key)){
            V oldValue = get(key);
            this.keyAndValues.remove(new Pair((K) key,get(key)));
            return oldValue;
        }
        // END
        return null;
    }

    @Override
    public final void putAll(Map<? extends K, ? extends V> m) {
        for (Entry<K, V> e : (Set<Entry<K, V>>) (Set) m.entrySet()) {
            put(e.getKey(), e.getValue());
        }
    }

    @Override
    public final void clear() {
        // BEGIN (write your solution here)
        keyAndValues.clear();
        // END
    }

    @Override
    public final Set<K> keySet() {
        final Set<K> keys = new HashSet<K>();
        for (Pair p : keyAndValues) {
            keys.add(p.getKey());
        }
        return keys;
    }

    @Override
    public final Collection<V> values() {
        // BEGIN (write your solution here)
        ArrayList<Object> vs = new ArrayList<>();
        for (int i =0; i < this.size(); i++){
            vs.add(keyAndValues.get(i).getValue());
        }
        return (Collection<V>) vs;
    }

    // END

    @Override
    public final Set<Entry<K, V>> entrySet() {
        return (Set<Entry<K, V>>) (Set) new HashSet<>(keyAndValues);
    }

    private final class Pair implements Entry<K, V> {

        private final K key;

        private V value;

        private Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V newValue) {
            final V oldValue = this.value;
            this.value = newValue;
            return oldValue;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            Entry<K, V> pair = (Entry<K, V>) o;


            if (key != null ? !key.equals(pair.getKey()) : pair.getKey() != null) {
                return false;
            }
            return !(value != null ? !value.equals(pair.getValue()) : pair.getValue() != null);

        }

        @Override
        public int hashCode() {
            return (key   == null ? 0 :   key.hashCode())
                    ^ (value == null ? 0 : value.hashCode());
        }
    }
}

