package com.egtinteractive.data_structures.map;

import java.util.Iterator;
import java.util.Objects;

public class HashMap<K, V> implements Map<K, V> {
    // -------------Fields
    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private int size;
    private int threshold;
    private final float loadFactor;
    private Node<K, V>[] table;

    // -------------
    
    // ------------- Node Inner class
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

	public K getKey() {
	    return key;
	}

	public Object getValue() {
	    return value;
	}

    }

    // -------------
    
    // ------------- Constructors
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

    // -------------
    
    // ------------- Private Methods
    private int hash(K key) {
	return key.hashCode() % table.length;
    }

    private void checkSize() {
	int current = 0;
	for (int index = 0; index < table.length; index++) {
	    if (table[index] != null) {
		current++;
	    }
	}
	if (current > threshold) {
	    resize();
	}

    }

    @SuppressWarnings("unchecked")
    private void resize() {

	Node<K, V>[] temp = new Node[this.table.length * 2];
	this.copy(table, temp);
	this.table = temp;
	threshold = (int) (table.length * loadFactor);

    }

    private void copy(Object[] src, Object[] dest) {
	if (dest.length < src.length) {
	    throw new RuntimeException(src + " cannot be copied into " + dest);
	}
	for (int index = 0; index < src.length; index++) {
	    dest[index] = src[index];
	}
    }

    private void putValue(K key, V value) {

	int hash = hash(key);

	Node<K, V> newNode = new Node<K, V>(key, value);

	if (table[hash] == null) {
	    table[hash] = newNode;
	} else {
	    Node<K, V> previous = null;
	    Node<K, V> current = table[hash];

	    while (current != null) {
		if (current.key.equals(key)) {
		    if (previous == null) {
			newNode.next = current.next;
			table[hash] = newNode;
			return;
		    } else {
			newNode.next = current.next;
			previous.next = newNode;
			return;
		    }
		}
		previous = current;
		current = current.next;
	    }
	    previous.next = newNode;
	}
    }
    // -------------

    // ------------- Methods from Interface
    @Override
    public V get(K key) {
	int hash = hash(key);
	if (table[hash] == null) {
	    return null;
	} else {
	    Node<K, V> temp = table[hash];
	    while (temp != null) {
		if (temp.key.equals(key)) {
		    return temp.value;

		}
		temp = temp.next;
	    }
	    return null;
	}
    }

    @Override
    public V put(K key, V value) {
	checkSize();
	putValue(key, value);
	size++;
	return value;
    }

    @Override
    public V remove(K key) {
	if (key == null) {
	    throw new NullPointerException("Null key value!");
	}

	int hash = hash(key);

	if (table[hash] == null) {
	    return null;
	} else {
	    Node<K, V> previous = null;
	    Node<K, V> current = table[hash];

	    while (current != null) { // we have reached last entry node of bucket.
		if (current.key.equals(key)) {
		    if (previous == null) { // delete first entry node.
			table[hash] = table[hash].next;
			size--;
			return current.value;
		    } else {
			previous.next = current.next;
			size--;
			return current.value;
		    }
		}
		previous = current;
		current = current.next;
	    }
	    return null;
	}

    }

    @Override
    public boolean containsKey(K key) {
	final int index = hash(key);
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

    @Override
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
	    hash = Objects.hashCode(itr.next().key) + Objects.hashCode(itr.next().value);
	}
	return 7 * Objects.hashCode(size) + 11 * hash;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (!(obj instanceof HashMap)) {
	    return false;
	}

	HashMap<K, V> newMap = (HashMap<K, V>) obj;
	if (size != newMap.size) {
	    return false;
	}

	boolean check = true;

	Iterator<Node<K, V>> newItr = newMap.iterator();

	while (newItr.hasNext()) {
	    if (this.containsKey((K) newItr.next().key) && (containsValue((V) newItr.next().value))) {
		check = true;
	    } else {
		check = false;
	    }
	}

	return check;

    }
    // -------------
}
