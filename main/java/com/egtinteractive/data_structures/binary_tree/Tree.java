package com.egtinteractive.data_structures.binary_tree;

import java.util.Iterator;

public interface Tree<T> {
    public void add(T element);

    public boolean remove(T element);

    public T lower(T e);

    public T higher(T e);

    public T pollFirst();

    public T pollLast();

    public int size();

    public void clear();

    public Iterator<T> iterator();

    public boolean equals(Object otherObject);

    public int hashCode();
}
