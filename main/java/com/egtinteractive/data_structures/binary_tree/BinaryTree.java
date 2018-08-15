package com.egtinteractive.data_structures.binary_tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BinaryTree<T extends Comparable<T>> implements Tree<T> {
    private int size;
    private Node<T> root;

    public BinaryTree() {
	this.size = 0;
    }

    private static class Node<T extends Comparable<T>> {

	T data;
	private Node<T> leftChild;
	private Node<T> rightChild;

	public Node(final T data) {
	    this.data = data;
	    this.leftChild = null;
	    this.rightChild = null;
	}

	public boolean insert(T element) {
	    if (data.compareTo(element) > 0) {
		if (leftChild != null) {
		    return leftChild.insert(element);
		} else {
		    leftChild = new Node<T>(element);
		    return true;
		}
	    } else if (data.compareTo(element) < 0) {
		if (rightChild != null) {
		    return rightChild.insert(element);
		} else {
		    rightChild = new Node<T>(element);
		    return true;
		}
	    } else {
		return false;
	    }
	}
    }

    private boolean validation(Node<T> node) {
	return node == null;
    }

    private void validateElement(final T element) {
	if (element == null) {
	    throw new NullPointerException("Can not add null element!");
	}
    }

    private void order(final Node<T> node, final List<T> list) {
	if (node == null) {
	    return;
	}
	order(node.leftChild, list);
	list.add(node.data);
	order(node.rightChild, list);

    }

    @Override
    public void add(T element) {
	validateElement(element);
	if (this.root == null) {
	    this.root = new Node<T>(element);
	    this.size++;
	    return;
	}
	if (root.insert(element)) {
	    this.size++;
	}

    }

    @Override
    public boolean remove(final T element) {
	validateElement(element);
	if (validation(root)) {
	    return false;
	}
	final int beforeSize = this.size();
	this.root = remove(element, this.root);
	if (beforeSize != this.size()) {
	    this.size = beforeSize - 1;
	    return true;
	}
	return false;
    }

    private Node<T> remove(final T element, final Node<T> node) {

	if (node == null) {
	    return node;
	}
	final int compare = element.compareTo(node.data);
	if (compare < 0) {
	    node.leftChild = remove(element, node.leftChild);
	} else if (compare > 0) {
	    node.rightChild = remove(element, node.rightChild);
	} else {
	    this.size--;
	    if (node.rightChild == null && node.leftChild == null) {
		return null;
	    } else if (node.rightChild == null) {
		return node.leftChild;
	    } else if (node.leftChild == null) {
		return node.rightChild;
	    } else {
		node.data = smallest(node.rightChild);
		node.rightChild = remove(node.data, node.rightChild);
	    }
	}
	return node;

    }

    private T smallest(Node<T> root) {
	T minValue = root.data;
	while (root.leftChild != null) {
	    minValue = root.leftChild.data;
	    root = root.leftChild;
	}
	return minValue;
    }

    @Override
    public T lower(T element) {
	validateElement(element);
	Node<T> newNode = lower(root, element);
	if (validation(newNode)) {
	    return null;
	}
	if (newNode.data.equals(element)) {
	    return null;
	}
	return newNode.data;
    }

    private Node<T> lower(Node<T> node, T element) {
	if (validation(node)) {
	    return null;
	}
	int compare = node.data.compareTo(element);
	if (compare >= 0) {
	    return lower(node.leftChild, element);
	}

	final Node<T> temp = lower(node.rightChild, element);
	if (temp == null) {
	    return node;
	} else {
	    return temp;
	}
    }

    @Override
    public T higher(T element) {
	validateElement(element);
	Node<T> newNode = higher(root, element);
	if (validation(newNode)) {
	    return null;
	}
	if (newNode.data.equals(element)) {
	    return null;
	}
	return newNode.data;
    }

    private Node<T> higher(Node<T> node, T element) {
	if (validation(node)) {
	    return null;
	}
	int compare = element.compareTo(node.data);
	if (compare >= 0) {
	    return higher(node.rightChild, element);
	}

	final Node<T> temp = higher(node.leftChild, element);
	if (temp == null) {
	    return node;
	} else {
	    return temp;
	}
    }

    @Override
    public T pollFirst() {
	if (validation(root)) {
	    return null;
	}
	return pollFirst(this.root, null);
    }

    private T pollFirst(final Node<T> node, final Node<T> parentNode) {
	if (node.leftChild == null) {
	    if (parentNode == null) {
		this.root = node.rightChild;
	    } else {
		parentNode.leftChild = node.rightChild;
	    }
	    this.size--;
	    return node.data;

	}
	return pollFirst(node.leftChild, node);
    }

    @Override
    public T pollLast() {
	if (validation(root)) {
	    return null;
	}
	return pollLast(this.root, null);
    }

    private T pollLast(final Node<T> node, final Node<T> parentNode) {
	if (node.rightChild == null) {
	    if (parentNode == null) {
		this.root = node.leftChild;
	    } else {
		parentNode.rightChild = node.leftChild;
	    }
	    this.size--;
	    return node.data;

	}
	return pollLast(node.rightChild, node);
    }

    @Override
    public int size() {
	return this.size;
    }

    @Override
    public void clear() {
	validation(root);
	root = null;
	this.size = 0;
    }

    public Iterator<T> iterator() {
	return new BinaryTreeIterator();
    }

    private class BinaryTreeIterator implements Iterator<T> {
	private final List<T> list = new ArrayList<>();
	private int index = -1;

	public BinaryTreeIterator() {
	    order(root, list);
	}

	@Override
	public boolean hasNext() {
	    return index < list.size() - 1;
	}

	@Override
	public T next() {
	    return list.get(++index);
	}

	@Override
	public void remove() {
	    if (index < 0 && index >= list.size()) {
		throw new IndexOutOfBoundsException("Index does not exist!");
	    }
	    BinaryTree.this.remove(list.get(index));
	}
    }

    @Override
    public boolean contains(T element) {
	if (element == null) {
	    return false;
	}
	return contains(element, root);
    }

    private boolean contains(T element, Node<T> node) {
	if (node == null) {
	    return false;
	}

	if (element.compareTo(node.data) < 0) {
	    return contains(element, node.leftChild);
	} else if (element.compareTo(node.data) > 0) {
	    return contains(element, node.rightChild);
	} else {
	    return true;
	}
    }

    @Override
    public boolean equals(Object o) {
	if (o == this)
	    return true;
	if (!(o instanceof Tree))
	    return false;
	int size = size();
	@SuppressWarnings("unchecked")
	Tree<T> newTree = (Tree<T>) o;

	if (newTree.size() != size) {
	    return false;
	}

	Iterator<T> itr = newTree.iterator();

	while (itr.hasNext()) {
	    final T current = itr.next();

	    if (!contains(current)) {
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
	    hashCode = 31 * hashCode + (itr.next().hashCode());

	}
	return hashCode;
    }
}
