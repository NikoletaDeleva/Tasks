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
    public void iteratorTestTwo(final BinaryTree<Integer> tree) {
	final int size = ThreadLocalRandom.current().nextInt(100,1000);
	List<Integer> list = new ArrayList<>();
	for (int index = 0; index < size; index++) {
	    final int num = ThreadLocalRandom.current().nextInt();
	    tree.add(num);
	    list.add(num);
	}
	list = list.stream().distinct().sorted().collect(Collectors.toList());
	final StringBuilder treeSb = new StringBuilder();
	final StringBuilder listSb = new StringBuilder();

	for (int index : tree) {
	    treeSb.append(index);
	}
	for (int index : list) {
	    listSb.append(index);
	}

	final boolean result = treeSb.toString().equals(listSb.toString());
	Assert.assertTrue(result);

    }

    @Test(dataProvider = "trees")
    public void iteratorTestOne(final BinaryTree<Integer> tree) {
	final int size = ThreadLocalRandom.current().nextInt(100,1000);
	for (int index = 0; index < size; index++) {
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
    public void iteratorTest(final BinaryTree<Integer> tree) {
	final int size = ThreadLocalRandom.current().nextInt(100,1000);
	List<Integer> list = new ArrayList<>();
	for (int index = 0; index < size; index++) {
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
