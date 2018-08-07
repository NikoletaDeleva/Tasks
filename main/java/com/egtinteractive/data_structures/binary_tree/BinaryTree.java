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

	final T data;
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

	public boolean remove(T element, Node<T> node) {
	    if (data.compareTo(element) > 0) {
		if (node.leftChild != null) {
		    return node.leftChild.remove(element, node.leftChild);
		} else {
		    return false;
		}
	    } else if (data.compareTo(element) < 0) {
		if (node.rightChild != null) {
		    return node.rightChild.remove(element, node.rightChild);
		} else {
		    return false;
		}
	    } else {
		if (node.leftChild == null && node.rightChild == null) {
		    node = null;
		    return true;
		} else if (node.rightChild == null) {
		    node = node.leftChild;
		    return true;
		} else if (leftChild == null) {
		    node = node.rightChild;
		    return true;
		} else {
		    Node<T> smallest = smallest(rightChild);
		    smallest.leftChild = leftChild;
		    return true;
		}
	    }
	}

	private Node<T> smallest(Node<T> node) {
	    if (node == null) {
		return null;
	    } else if (node.leftChild == null) {
		return node;
	    }
	    return node.leftChild;
	}

	private Node<T> biggest(Node<T> node) {
	    if (node == null) {
		return null;
	    } else if (node.rightChild == null) {
		return node;
	    }
	    return biggest(node.rightChild);
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

    private void оrder(final Node<T> node, final List<T> list) {
	if (node == null) {
	    return;
	}
	оrder(node.leftChild, list);
	list.add(node.data);
	оrder(node.rightChild, list);

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
    public boolean remove(T element) {
	validateElement(element);
	if (validation(root)) {
	    return false;
	}
	if (!root.remove(element, root)) {
	    return false;
	} else {
	    this.size--;
	    return true;
	}
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
	T info;
	if (validation(root)) {
	    return null;
	}
	if (root.leftChild == null) {
	    info = root.data;
	    root = root.rightChild;
	} else {
	    Node<T> smallest = root.smallest(root);
	    info = smallest.data;
	    smallest = null;
	}
	size--;
	return info;
    }

    @Override
    public T pollLast() {
	T info;
	if (validation(root)) {
	    return null;
	}
	if (root.rightChild == null) {
	    info = root.data;
	    root = root.leftChild;
	} else {
	    Node<T> biggest = root.biggest(root);
	    info = biggest.data;
	    biggest = null;
	}
	size--;
	return info;
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

    @Override
    public Iterator<T> iterator() {
	return new BinaryTreeIterator();
    }

    private class BinaryTreeIterator implements Iterator<T> {
	private final List<T> list = new ArrayList<>();
	private int index = -1;

	public BinaryTreeIterator() {
	    оrder(root, list);
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
    public boolean equals(Object o) {
	if (o == this)
	    return true;
	if (!(o instanceof Tree))
	    return false;
	int size = size();
	if (size != ((BinaryTree<?>) o).size())
	    return false;

	Iterator<T> itrOne = iterator();
	Iterator<?> itrTwo = ((Tree<?>) o).iterator();

	while (--size >= 0) {
	    if (!itrOne.next().equals(itrTwo.next())) {
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
