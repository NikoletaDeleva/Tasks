package com.egtinteractive.data_structures.list;

public interface List<T> extends Iterable<T>  {
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

}
