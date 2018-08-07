package com.egtinteractive.data_structures.list;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class LinkedList<T> implements List<T> {

   
    private static class Node<T> {
	private T data;
	private Node<T> next;
	private Node<T> previos;


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

	private void setPrevios(Node<T> previos) {
	    this.previos = previos;
	}

    }

    private int size = 0;
    private Node<T> head;
    private Node<T> tail;

    public LinkedList() {
    }

    private void indexValidation(int index) {
	if (index < 0 || index >= size) {
	    throw new IndexOutOfBoundsException("There is no such index in that ArrayList!");
	}
    }

    private Node<T> iterateLinkedList(int index, Node<T> current) {
	for (int position = 0; position < index; position++) {
	    current = current.getNext();
	}
	return current;
    }

    @Override
    public T get(int index) {
	indexValidation(index);
	Node<T> current = this.head;
	current = iterateLinkedList(index, current);
	return (T) current.data;
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
	indexValidation(index);

	if (index == this.size) {
	    add(element);
	} else if (index == 0) {
	    final Node<T> newNode = new Node<T>(element, null);
	    head.previos = newNode;
	    head = newNode;
	} else {
	    Node<T> current = this.head;
	    current = iterateLinkedList(index, current);
	    final Node<T> newNode = new Node<T>(element, current.previos);
	    current.previos = newNode;
	    current.previos.next = newNode;
	    newNode.next = current;

	}
	this.size++;
    }

    @Override
    public void set(int index, T element) {
	indexValidation(index);
	Node<T> current = head;
	current = iterateLinkedList(index, current);
	current.data = element;
    }

    @Override
    public boolean remove(T element) {
	int index = indexOf(element);
	if (index != -1) {
	    remove(index);
	}
	return false;
    }

    @Override
    public boolean remove(int index) {
	indexValidation(index);
	
	if (index == 0) {
	    this.head = this.head.getNext();
	    size--;
	    return true;
	}
	
	Node<T> current = this.head;
	current = iterateLinkedList(index-1, current);

	final Node<T> nextNode = current.getNext().getNext();
	current.setNext(nextNode);
	
	size--;
	return true;
    }

    @Override
    public boolean contains(T element) {
	return indexOf(element) != -1;
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
	    private int previous = 0;

	    @Override
	    public boolean hasNext() {
		return current != null;
	    }

	    @Override
	    public T next() {
		if (hasNext()) {
		    T data = current.data;
		    current = current.next;
		    previous++;
		    return data;
		}
		throw new NoSuchElementException("No such element!");
	    }

	    @Override
	    public void remove() {
		LinkedList.this.remove(--previous);
		size--;
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

	List<?> temp = (List<?>) o;
	if (size != temp.size())
	    return false;

	Iterator<T> itr1 = iterator();
	Iterator<?> itr2 = temp.iterator();

	while (--size >= 0) {
	    if (!Objects.equals(itr1.next(), itr2.next())) {
		return false;
	    }
	}
	return true;
    }

    @Override
    public int hashCode() {
	Iterator<T> itr = iterator();
	int hash = 0;
	while (itr.hasNext()) {
	    hash += Objects.hashCode(itr.next());
	}
	return 7 * Objects.hashCode(size) + 11 * hash;
    }
}
