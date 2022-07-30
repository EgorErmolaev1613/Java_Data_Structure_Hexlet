package org.hexlet.JavaDataStructure.lesson11;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Collection;

public class ArrayBasedMap<K, V> implements Map<K, V> {

    private List<Pair<K, V>> keyAndValues = new ArrayList<>();

    @Override
    public final int size() {
        return keyAndValues.size();
    }

    @Override
    public final boolean isEmpty() {
        return keyAndValues.isEmpty();
    }

    @Override
    public final boolean containsKey(Object key) {
        for (Pair<K, V> p : keyAndValues) {
            if (p.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public final boolean containsValue(Object value) {
        for (Pair<K, V> p : keyAndValues) {
            if (p.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public final V get(Object key) {
        if (key == null) {
            return null;
        }

        for (Pair<K, V> p : keyAndValues) {
            if (p.getKey().equals(key)) {
                return p.getValue();
            }
        }
        return null;
    }

    @Override
    public final V getOrDefault(Object key, V defaultValue) {
        // BEGIN (write your solution here)
        V v;
        return (((v = get(key))!=null || containsKey(key))
        ? v : defaultValue);

        // END
    }

    @Override
    public final V put(K key, V value) {
        V result = value;
        if (!containsKey(key)) {
            keyAndValues.add(new Pair<>(key, value));
            return null;
        }
        for (Pair<K, V> p : keyAndValues) {
            if (p.getKey().equals(key)) {
                result = p.getValue();
                p.setValue(value);
                break;
            }
        }
        return result;
    }

    @Override
    public final V remove(Object key) {
        V value;
        for (int i = 0; i < keyAndValues.size(); i++) {
            final Pair<K, V> p = keyAndValues.get(i);
            if (p.getKey().equals(key)) {
                value = p.getValue();
                keyAndValues.remove(i);
                return value;
            }
        }
        return null;
    }

    @Override
    public final void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<K, V> e : (Set<Map.Entry<K, V>>) (Set) m.entrySet()) {
            put(e.getKey(), e.getValue());
        }
    }

    @Override
    public final void clear() {
        keyAndValues.clear();
    }

    @Override
    public final Set<K> keySet() {
        final Set<K> keys = new HashSet<>();
        for (Pair<K, V> p : keyAndValues) {
            keys.add(p.getKey());
        }
        return keys;
    }

    @Override
    public final Collection<V> values() {
        final List<V> val = new ArrayList<>();
        for (Pair<K, V> p : keyAndValues) {
            val.add(p.getValue());
        }
        return val;
    }

    @Override
    public final Set<Entry<K, V>> entrySet() {
        return new HashSet<>(keyAndValues);
    }

    private static final class Pair<K, V> implements Map.Entry<K, V> {

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

            Map.Entry<K, V> pair = (Map.Entry<K, V>) o;


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
