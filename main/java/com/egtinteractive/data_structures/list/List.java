package com.egtinteractive.data_structures.list;

import java.util.Iterator;

public interface List<T> {
    public T get(int index);

    public void add(T element);

    public void add(int index, T element);

    public void set(int index, T element);

    public boolean remove(T element);

    public boolean remove(int index);

    public boolean contains(T element);

    public int indexOf(T element);

    public int size();

    public void clear();

    public Iterator<T> iterator();

    public boolean equals(Object otherObject);

    public int hashCode();

}
