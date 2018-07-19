package com.egtinteractive.data_structures.list;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    // ------------- Fields
    private T[] array;
    private int size = 0;
    public static final int DEFAULT_SIZE = 20;
    // -------------

    // -------------Constructors
    @SuppressWarnings("unchecked")
    public ArrayList() {
	this.array = (T[]) new Object[DEFAULT_SIZE];
    }

    @SuppressWarnings("unchecked")
    public ArrayList(int size) {
	this.array = (T[]) new Object[size];
    }
    // -------------

    // -------------Private Methods
    private void checkCapacity() {
	if (this.size < this.array.length) {
	    return;
	}
	this.reSize();
    }

    @SuppressWarnings("unchecked")
    private void reSize() {
	T[] temp = (T[]) new Object[this.array.length * 2];
	this.copy(array, temp);
	this.array = temp;
    }

    private void copy(Object[] src, Object[] dest) {
	if (dest.length < src.length) {
	    throw new RuntimeException(src + " cannot be copied into " + dest);
	}
	for (int index = 0; index < src.length; index++) {
	    dest[index] = src[index];
	}
    }

    private void indexValidation(int index) {
	if (index < 0 || index >= size) {
	    throw new IndexOutOfBoundsException("There is no such index in that ArrayList!");
	}
    }

    private int indexOfElement(T element) {
	for (int index = 0; index < this.size; index++) {
	    if (Objects.equals(this.array[index], element)) {
		return index;
	    }
	}
	return -1;
    }
    // -------------

    // ------------- Methods from Interface
    @Override
    public T get(int index) {
	indexValidation(index);
	T element = this.array[index];
	return element;
    }

    @Override
    public void add(T element) {
	this.checkCapacity();
	this.array[this.size++] = element;

    }

    @Override
    public void add(int index, T element) {
	indexValidation(index);
	if (index == this.size) {
	    this.add(element);
	} else {
	    this.size++;
	    this.checkCapacity();
	    for (int pos = this.size - 1; pos >= index; pos--) {
		if (pos != 0) {
		    this.array[pos + 1] = this.array[pos];
		}
	    }
	    this.array[index] = element;
	}
    }

    @Override
    public void set(int index, T element) {
	indexValidation(index);
	this.array[index] = element;
    }

    @Override
    public boolean remove(T element) {
	int index = indexOfElement(element);
	if (index == -1) {
	    return false;
	}
	return remove(index);
    }

    @Override
    public boolean remove(int index) {
	indexValidation(index);
	if (index != --size) {
	    for (int pos = index; pos < size; pos++) {
		this.array[pos] = array[pos + 1];
	    }
	}
	this.array[size] = null;
	return true;
    }

    @Override
    public boolean contains(T element) {
	if (size != 0) {
	    for (int index = 0; index < this.size; index++) {
		if (Objects.equals(this.array[index], element)) {
		    return true;
		}
	    }
	}
	return false;
    }

    @Override
    public int indexOf(T element) {
	return indexOfElement(element);
    }

    @Override
    public int size() {
	return this.size;
    }

    @Override
    public void clear() {
	this.array = null;
	this.size = 0;
    }

    @Override
    public Iterator<T> iterator() {
	final Iterator<T> iterator = new Iterator<T>() {
	    private int index = 0;

	    @Override
	    public boolean hasNext() {
		return index < size - 1;
	    }

	    @Override
	    public T next() {
		if (hasNext()) {
		    return array[index++];
		} else {
		    throw new NoSuchElementException();
		}
	    }

	    @Override
	    public void remove() {
		indexValidation(index);
		ArrayList.this.remove(index--);
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
	Iterator<?> itr2 = ((ArrayList<?>) o).iterator();

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
	    hash = Objects.hashCode(itr.next());
	}
	return 7 * Objects.hashCode(size) + 11 * hash;
    }
    // -------------
}
