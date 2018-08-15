package com.egtinteractive.data_structures.binary_tree;

public interface Tree<T> extends Iterable<T> {
    public void add(T element);

    public boolean remove(T element);

    public T lower(T e);

    public T higher(T e);

    public T pollFirst();

    public T pollLast();

    public int size();

    public void clear();
    
    public boolean contains(T e);
}
