package com.egtinteractive.data_structures.map;

public interface Map<K, V> extends Iterable<HashMap.Node<K, V>>{
    public V get(K key);

    public V put(K key, V value);

    public V remove(K key);

    public boolean containsKey(K key);

    public boolean containsValue(V value);

    public int size();

    public void clear();

}
