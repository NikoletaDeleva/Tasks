package com.egtinteractive.data_structures.map;

import java.util.Iterator;
import java.util.Objects;

public class HashMap<K, V> implements Map<K, V> {

    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private int size;
    private int threshold;
    private final float loadFactor;
    private Node<K, V>[] table;

    static class Node<K, V> {
	private final K key;
	private V value;
	private Node<K, V> next;

	Node(K key, V value, Node<K, V> next) {
	    this.key = key;
	    this.value = value;
	    this.next = next;
	}

	Node(K key, V value) {
	    this(key, value, null);
	}

	public Node<K, V> getNext() {
	    return next;
	}

	public void setNext(Node<K, V> next) {
	    this.next = next;
	}

	public K getKey() {
	    return key;
	}

	public V getValue() {
	    return value;
	}

	public void setValue(final V value) {
	    this.value = value;
	}

    }

    public HashMap() {
	this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public HashMap(int capacity) {
	this(capacity, DEFAULT_LOAD_FACTOR);
    }

    @SuppressWarnings("unchecked")
    public HashMap(int capacity, float loadFactor) {
	if (capacity < 0) {
	    throw new IllegalArgumentException("Illegal initial capacity");
	} else {
	    if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
		throw new IllegalArgumentException("Illegal load factor");
	    } else {
		this.loadFactor = loadFactor;
		this.table = new Node[capacity];
		this.threshold = (int) (table.length * loadFactor);
	    }
	}
    }

    private int hash(K key, int tableLenght) {
	if (key == null) {
	    return 0;
	} else {
	    return Math.abs(key.hashCode() % tableLenght);
	}
    }

    private void checkSize() {
	if (this.threshold == this.size) {
	    resize();
	}

    }

    @SuppressWarnings("unchecked")
    private void resize() {
	Node<K, V>[] temp = new Node[this.table.length * 2];
	this.copy(temp);
	this.table = temp;
	this.threshold = (int) (table.length * loadFactor);

    }

    private void copy(Node<K, V>[] dest) {
	if (dest.length < table.length) {
	    throw new RuntimeException(table + " cannot be copied into " + dest);
	}
	this.size = 0;
	for (int index = 0; index < table.length; index++) {
	    Node<K, V> node = table[index];
	    while (node != null) {
		putValue(node.getKey(), node.getValue(), dest);
		node = node.getNext();
	    }
	}
    }

    @Override
    public V put(K key, V value) {
	checkSize();
	putValue(key, value, table);
	return value;
    }

    private void putValue(K key, V value, Node<K, V>[] table) {
	final int hash = hash(key, table.length);
	final Node<K, V> newNode = new Node<>(key, value);

	Node<K, V> current = table[hash];

	if (current == null) {
	    table[hash] = newNode;
	    size++;
	    return;
	}

	while (current.getNext() != null) {
	    if (Objects.equals(current.getKey(), key)) {
		current.setValue(value);
		return;
	    }
	    current = current.getNext();
	}

	if (Objects.equals(current.getKey(), key)) {
	    current.setValue(value);
	    return;
	}
	current.setNext(newNode);
	size++;
    }

    @Override
    public V get(K key) {
	int hash = hash(key, table.length);

	Node<K, V> temp = table[hash];
	while (temp != null) {
	    if (Objects.equals(temp.key, key)) {
		return temp.value;
	    }
	    temp = temp.getNext();
	}
	return null;
    }

    @Override
    public V remove(K key) {
	int hash = hash(key, table.length);

	if (table[hash] == null) {
	    return null;
	} else {
	    Node<K, V> current = table[hash];
	    Node<K, V> previous = null;
	    while (current != null) {
		if (Objects.equals(current.key, key)) {
		    if (previous == null) {
			table[hash] = current.getNext();
			size--;
			return current.getValue();
		    }
		    final V value = current.getValue();
		    current = current.getNext();
		    size--;
		    return value;
		}
		previous = current;
		current = current.getNext();
	    }
	    return null;
	}

    }

    @Override
    public boolean containsKey(K key) {
	final int index = hash(key, table.length);
	Node<K, V> temp = table[index];
	while (temp != null) {
	    if (Objects.equals(temp.getKey(), key)) {
		return true;
	    }
	    temp = temp.getNext();
	}
	return false;
    }

    @Override
    public boolean containsValue(V value) {
	for (int index = 0; index < table.length; index++) {
	    Node<K, V> temp = table[index];
	    while (temp != null) {
		if (Objects.equals(temp.getValue(), value)) {
		    return true;
		}
		temp = temp.getNext();
	    }
	}
	return false;
    }

    @Override
    public int size() {
	return this.size;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
	if (table != null && size > 0) {
	    this.size = 0;
	    table = new Node[DEFAULT_CAPACITY];
	}

    }

    public Iterator<Node<K, V>> iterator() {
	final Iterator<Node<K, V>> iterator = new Iterator<Node<K, V>>() {

	    private int index = 0;
	    private Node<K, V> marker = table[index];
	    private Node<K, V> current = null;
	    private boolean canRemove = false;

	    @Override
	    public boolean hasNext() {

		while (index < table.length && marker == null) {

		    marker = table[index];
		    if (marker == null)
			index++;

		}

		if (index < table.length) {
		    return true;
		}

		return false;
	    }

	    @Override
	    public Node<K, V> next() {
		if (hasNext()) {
		    current = marker;
		    marker = marker.getNext();
		    if (marker == null)
			index++;
		    canRemove = true;
		    return current;
		}
		return null;
	    }

	    @Override
	    public void remove() {
		if (!canRemove) {
		    throw new IllegalStateException("Cannot remove element");
		}

		HashMap.this.remove(current.getKey());
		canRemove = false;
	    }
	};
	return iterator;
    }

    @Override
    public int hashCode() {
	Iterator<Node<K, V>> itr = iterator();
	int hash = 0;
	while (itr.hasNext()) {
	    Node<K, V> next = itr.next();
	    K nextKey = next.key;
	    V nextValue = next.value;

	    int hashKey;
	    int hashValue;

	    if (nextKey == null) {
		hashKey = 0;
	    } else {
		hashKey = Objects.hashCode(nextKey);
	    }

	    if (nextValue == null) {
		hashValue = 0;
	    } else {
		hashValue = Objects.hashCode(nextValue);
	    }

	    hash = hashKey + hashValue;
	}
	return 7 * Objects.hashCode(size) + 11 * hash;
    }

    @Override
    public boolean equals(Object o) {
	if (o == this)
	    return true;
	if (!(o instanceof Map))
	    return false;
	int size = size();
	if (size != ((Map<?, ?>) o).size())
	    return false;

	Iterator<Node<K, V>> itr1 = iterator();
	Iterator<Node<?, ?>> itr2 = ((Map) o).iterator();

	while (--size >= 0) {
	    Node<K, V> myCurrEntry = itr1.next();
	    Node<?, ?> otherCurrEntry = itr2.next();
	    if (!Objects.equals(myCurrEntry.getKey(), otherCurrEntry.getKey())
		    || !Objects.equals(myCurrEntry.getValue(), otherCurrEntry.getValue())) {
		return false;

	    }
	}

	return true;
    }
}
