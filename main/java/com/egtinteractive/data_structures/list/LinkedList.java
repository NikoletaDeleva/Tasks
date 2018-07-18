package com.egtinteractive.data_structures.list;

import java.util.Iterator;
import java.util.Objects;

public class LinkedList<T> implements List<T> {

    @SuppressWarnings({ "unused" })
    private static class Node<T> {
	private T data;
	private Node<T> next;
	private Node<T> previos;

	Node() {
	    setData(null);
	    setNext(null);
	}

	Node(final T element, Node<T> previos) {
	    setData(element);

	    this.setPrevios(previos);
	    this.setNext(null);
	    if (previos != null) {
		previos.next = this;
	    }

	}

	@Override
	public String toString() {
	    return "Node [data=" + data + "]";
	}

	private void setNext(Node<T> next) {
	    this.next = next;
	}

	private Node<T> getNext() {
	    return this.next;
	}

	private void setData(T element) {
	    this.data = element;
	}

	private T getData() {
	    return this.data;
	}

	private Node<T> getPrevios() {
	    return this.previos;
	}

	private void setPrevios(Node<T> previos) {
	    this.previos = previos;
	}

    }

    private int size = 0;
    private Node<T> head;
    private Node<T> tail;

    public LinkedList() {
    }

    @Override
    public T get(int index) {
	if (index >= this.size) {
	    throw new RuntimeException("No such element");
	}
	Node<T> current = this.head;
	current = iterateLinkedList(index, current);
	return (T) current.data;
    }

    private Node<T> iterateLinkedList(int index, Node<T> current) {
	for (int position = 0; position < index; position++) {
	    current = current.next;
	}
	return current;
    }

    @Override
    public void add(T element) {
	if (this.head == null) {
	    this.head = new Node<T>(element, null);
	    this.tail = head;
	} else {
	    final Node<T> newNode = new Node<T>(element, tail);
	    this.tail = newNode;
	}
	size++;
    }

    @Override
    public void add(int index, T element) {
	if (index > this.size) {
	    throw new RuntimeException("No such position");
	} else {
	    if (index == this.size) {
		this.add(element);
	    } else {
		Node<T> current = this.head;

		current = iterateLinkedList(index, current);

		final Node<T> newNode = new Node<T>(element, current.previos);
		if (current.previos != null) {
		    current.previos.next = newNode;
		}
		newNode.next = current;
		this.size++;
	    }
	}
    }

    @Override
    public void set(int index, T element) {
	if (index >= this.size || index < 0) {
	    throw new RuntimeException("No such index");
	} else {
	    Node<T> current = head;
	    current = iterateLinkedList(index, current);
	    current.data = element;
	}
    }

    @Override
    public boolean remove(T element) {
	if (this.contains(element)) {
	    int index = indexOf(element);
	    remove(index);
	}
	return false;
    }

    @Override
    public boolean remove(int index) {
	if (index >= this.size || index < 0) {
	    throw new RuntimeException("No such index");
	} else {
	    Node<T> current = head;
	    current = iterateLinkedList(index, current);

	    if (current.next != null && current.previos != null) {
		current.previos.next = current.next;
		current.next.previos = current.previos;
	    }
	    current.previos = null;
	    current.next = null;
	    current.data = null;
	    size--;
	    return true;
	}
    }

    @Override
    public boolean contains(T element) {
	if (this.size != 0) {
	    for (int pos = 0; pos < size; pos++) {
		T currentNode = get(pos);
		if (Objects.equals(currentNode, element)) {
		    return true;
		}
	    }
	}
	return false;
    }

    @Override
    public int indexOf(T element) {
	if (this.size != 0) {
	    for (int pos = 0; pos < size; pos++) {
		T currentNode = get(pos);
		if (Objects.equals(currentNode, element)) {
		    return pos;
		}
	    }
	}
	return -1;
    }

    @Override
    public int size() {
	return this.size;
    }

    @Override
    public void clear() {
	this.size = 0;
	this.head = null;
	this.tail = null;

    }

    @Override
    public Iterator<T> iterator() {
	final Iterator<T> iterator = new Iterator<T>() {
	    private Node<T> current = head;

	    @Override
	    public boolean hasNext() {
		return current != null;
	    }

	    @Override
	    public T next() {
		if (hasNext()) {
		    T data = current.data;
		    current = current.next;
		    return data;
		}
		return null;
	    }

	};
	return iterator;
    }

    @Override
    public boolean equals(Object o) {
	if (o == this)
	    return true;
	if (!(o instanceof List))
	    return false;
	int size = size();
	if (size != ((List<?>) o).size())
	    return false;

	Iterator<T> itr1 = iterator();
	Iterator<?> itr2 = ((LinkedList<?>) o).iterator();

	while (--size >= 0) {
	    if (!Objects.equals(itr1.next(), itr2.next())) {
		return false;
	    }
	}
	return true;
    }

    @Override
    public int hashCode() {
	int hashCode = size;
	Iterator<T> itr = iterator();
	int pos = size();
	while (--pos >= 0) {
	    final T next = itr.next();
	    if (next != null) {
		hashCode = 31 * hashCode + next.hashCode();
	    }

	}
	return hashCode;
    }

}
