package com.egtinteractive.data_structures.binary_tree_tests;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.data_structures.binary_tree.BinaryTree;

public class IteratorTest {
    @DataProvider(name = "trees")
    public Object[][] createData() {
	return new Object[][] { { new BinaryTree<>() } };
    }

    @Test(dataProvider = "trees")
    public void iteratorShouldReturnOrderedSet(final BinaryTree<Integer> tree) {
	final int size = ThreadLocalRandom.current().nextInt(100,1000);
	List<Integer> list = new ArrayList<>();
	for (int i = 0; i < size; i++) {
	    final int num = ThreadLocalRandom.current().nextInt();
	    tree.add(num);
	    list.add(num);
	}
	list = list.stream().distinct().sorted().collect(Collectors.toList());
	final StringBuilder treeSb = new StringBuilder();
	final StringBuilder listSb = new StringBuilder();

	for (int i : tree) {
	    treeSb.append(i);
	}
	for (int i : list) {
	    listSb.append(i);
	}

	final boolean result = treeSb.toString().equals(listSb.toString());
	Assert.assertTrue(result);

    }

    @Test(dataProvider = "trees")
    public void iteratorShouldRemove(final BinaryTree<Integer> tree) {
	final int size = ThreadLocalRandom.current().nextInt(100,1000);
	for (int i = 0; i < size; i++) {
	    final int num = ThreadLocalRandom.current().nextInt();
	    tree.add(num);
	}

	Iterator<Integer> iterator = tree.iterator();

	while (iterator.hasNext()) {
	    iterator.next();
	    iterator.remove();
	}
	Assert.assertEquals(tree.size(), 0);
    }

    @Test(dataProvider = "trees")
    public void iteratorShouldNextRemoveAndNextCorrectly(final BinaryTree<Integer> tree) {
	final int size = ThreadLocalRandom.current().nextInt(100,1000);
	List<Integer> list = new ArrayList<>();
	for (int i = 0; i < size; i++) {
	    final int num = ThreadLocalRandom.current().nextInt();
	    tree.add(num);
	    list.add(num);
	}

	Iterator<Integer> listIterator = list.stream().distinct().sorted().collect(Collectors.toList()).iterator();
	Iterator<Integer> iterator = tree.iterator();
	int pos = tree.size();

	while (pos-- > 35) {
	    Assert.assertEquals(iterator.next(), listIterator.next());
	}

	while (pos-- > 20) {
	    iterator.remove();
	    listIterator.remove();
	    Assert.assertEquals(iterator.next(), listIterator.next());
	}

	while (pos-- >= 0) {
	    Assert.assertEquals(iterator.next(), listIterator.next());
	}

    }

}
