package com.egtinteractive.data_structures.binary_tree;

import java.util.Iterator;

public class BinaryTree<T extends Comparable<T>> implements Tree<T> {

    private static class Node<T extends Comparable<T>> {
	final T data;

	private Node<T> leftChild;
	private Node<T> rightChild;
	private Node<T> parent;

	public Node(final T data) {
	    this.data = data;
	    this.leftChild = null;
	    this.rightChild = null;
	}

	public void insert(T element) {
	    if (data.compareTo(element) > 0) {
		if (leftChild != null) {
		    leftChild.insert(element);
		} else {
		    leftChild = new Node<T>(element);
		}
	    } else {
		if (rightChild != null) {
		    rightChild.insert(element);
		} else {
		    rightChild = new Node<T>(element);
		}
	    }
	}

	public void remove(T element) {
	    if (data.compareTo(element) == 0) {
		if (leftChild == null && rightChild == null) {

		    if (parent.leftChild.data.compareTo(element) == 0) {
			parent.leftChild = null;
		    } else if (parent.rightChild.data.compareTo(element) == 0) {
			parent.rightChild = null;
		    }

		} else {
		    if (leftChild == null) {

			if (parent.data.compareTo(rightChild.data) < 0) {
			    parent.rightChild = rightChild;
			    rightChild.parent = parent;
			    rightChild = null;
			    parent = null;
			} else {
			    parent.leftChild = rightChild;
			    leftChild.parent = leftChild;
			    leftChild = null;
			    parent = null;
			}

		    } else if (rightChild == null) {

			if (parent.data.compareTo(leftChild.data) > 0) {
			    parent.leftChild = leftChild;
			    leftChild.parent = parent;
			    leftChild = null;
			    parent = null;
			} else {
			    parent.rightChild = leftChild;
			    leftChild.parent = parent;
			    leftChild = null;
			    parent = null;
			}

		    } else {
			if (parent.data.compareTo(rightChild.data) < 0) {
			    parent.rightChild = rightChild;
			} else {
			    parent.leftChild = rightChild;
			}

			Node<T> smallest = smallest(rightChild);
			leftChild.parent = smallest;
			smallest.leftChild = leftChild;
		    }
		}
		return;
	    } else {
		if (data.compareTo(element) < 0) {
		    leftChild.remove(element);
		} else {
		    rightChild.remove(element);
		}
	    }
	}

	private Node<T> smallest(Node<T> node) {
	    if (leftChild == null) {
		return node;
	    } else {
		smallest(node.leftChild);
	    }
	    return null;
	}

	private Node<T> biggest(Node<T> node) {
	    if (rightChild == null) {
		return node;
	    } else {
		biggest(node.rightChild);
	    }
	    return null;
	}

    }

    private int size;
    private Node<T> root;

    private boolean exists(T element, Node<T> node) {
	if (node == null) {
	    return false;
	}
	if (node.data.compareTo(element) == 0) {
	    return true;
	} else {
	    if (node.data.compareTo(element) < 0) {
		node = node.leftChild;
		exists(element, node);
	    } else {
		node = node.rightChild;
		exists(element, node);
	    }
	}
	return false;
    }

    @Override
    public void add(T element) {
	if (element == null) {
	    throw new NullPointerException("Can not add null element!");
	}
	if (root == null) {
	    this.root = new Node<T>(element);
	    size++;
	}
	if (!exists(element, root)) {
	    root.insert(element);
	    size++;
	}

    }

    @Override
    public boolean remove(T element) {
	if (element == null) {
	    throw new NullPointerException("Can not add null element!");
	}
	if (root == null) {
	    throw new NullPointerException("Tree is empty!");
	}
	if (!exists(element, root)) {
	    return true;
	}
	root.remove(element);
	size--;
	return true;
    }

    @Override
    public T lower(T e) {
	if (e == null) {
	    throw new IllegalArgumentException();
	}
	Node<T> newNode = lower(root, e);
	if (newNode == null) {
	    return null;
	}
	if (newNode.data.equals(e)) {
	    return null;
	}
	return newNode.data;
    }

    private Node<T> lower(Node<T> node, T element) {
	if (node == null) {
	    return null;
	}
	int compare = element.compareTo(node.data);
	if (compare <= 0) {
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
    public T higher(T e) {
	if (e == null) {
	    throw new IllegalArgumentException();
	}
	Node<T> newNode = higher(root, e);
	if (newNode == null) {
	    return null;
	}
	if (newNode.data.equals(e)) {
	    return null;
	}
	return newNode.data;
    }

    private Node<T> higher(Node<T> node, T element) {
	if (node == null) {
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
	if (root == null) {
	    return null;
	}
	if (root.leftChild == null && root.rightChild == null) {
	    T info = root.data;
	    root = null;
	    return info;
	}
	if (root.leftChild == null) {
	    T info = root.data;
	    root.rightChild.parent = null;
	    root = root.rightChild;
	    size--;
	    return info;
	}
	if (root.leftChild != null) {
	    Node<T> smallest = root.smallest(root);
	    T info = smallest.data;
	    smallest.parent = null;
	    smallest.parent.leftChild = null;
	    size--;
	    return info;
	}
	return null;
    }

    @Override
    public T pollLast() {
	if (root == null) {
	    return null;
	}
	if (root.leftChild == null && root.rightChild == null) {
	    T info = root.data;
	    root = null;
	    size--;
	    return info;
	}
	if (root.rightChild == null) {
	    T info = root.data;
	    root.leftChild.parent = null;
	    root = root.leftChild;
	    size--;
	    return info;
	}
	if (root.rightChild != null) {
	    Node<T> biggest = root.biggest(root);
	    T info = biggest.data;
	    biggest.parent = null;
	    biggest.parent.rightChild = null;
	    size--;
	    return info;
	}
	return null;
    }

    @Override
    public int size() {
	return this.size;
    }

    @Override
    public void clear() {
	if (root != null) {
	    root.leftChild = null;
	    root.rightChild = null;
	    root = null;
	    this.size = 0;
	} else {
	    throw new RuntimeException("The tree is empty!");
	}

    }

    @Override
    public Iterator<T> iterator() {
	final Iterator<T> iterator = new Iterator<T>() {

	    @Override
	    public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	    }

	    @Override
	    public T next() {
		// TODO Auto-generated method stub
		return null;
	    }

	};
	return iterator;
    }


}
