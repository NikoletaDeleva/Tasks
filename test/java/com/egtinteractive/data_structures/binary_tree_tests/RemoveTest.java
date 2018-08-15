package com.egtinteractive.data_structures.binary_tree_tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.data_structures.binary_tree.BinaryTree;

public class RemoveTest {
    @DataProvider(name = "trees")
    public Object[][] createData() {
	return new Object[][] { { new BinaryTree<>() } };
    }

    @Test(dataProvider = "trees")
    public void f(final BinaryTree<Integer> tree) {
	final int randSize = ThreadLocalRandom.current().nextInt(1, 10);
	final int randNumb = ThreadLocalRandom.current().nextInt(100, 200);

	for (int index = 0; index < randSize; index++) {
	    final int randData = ThreadLocalRandom.current().nextInt(0, 100);
	    tree.add(randData);
	}

	final int treeSize = tree.size();

	tree.add(randNumb);

	tree.remove(randNumb);

	final int realSize = tree.size();

	assertEquals(treeSize, realSize);
    }

    @Test(dataProvider = "trees")
    public void second(final BinaryTree<Integer> tree) {
	final int randSize = ThreadLocalRandom.current().nextInt(1, 100);

	for (int index = 0; index < randSize; index++) {
	    final int randData = ThreadLocalRandom.current().nextInt(0, 100);
	    tree.add(randData);
	}

	assertFalse(tree.remove(ThreadLocalRandom.current().nextInt(-100, 0)));
    }

    @Test(dataProvider = "trees")
    public void treeTest(final BinaryTree<Integer> tree) {
	final int size = ThreadLocalRandom.current().nextInt(100, 500);
	List<Integer> list = new ArrayList<>();

	for (int index = 0; index < size; index++) {
	    final int number = ThreadLocalRandom.current().nextInt(100, 1000);
	    list.add(number);
	    tree.add(number);
	}
	list = list.stream().distinct().sorted((x, y) -> x.compareTo(y)).collect(Collectors.toList());

	for (int index = 0; index < 10; index++) {
	    final int pos = ThreadLocalRandom.current().nextInt(0, list.size());
	    int a = list.remove(pos);
	    tree.remove(a);
	}

	assertEquals(list.size(), tree.size());

	Collections.sort(list);
	Iterator<Integer> listIter = list.iterator();
	Iterator<Integer> treeIter = tree.iterator();

	while (treeIter.hasNext() && listIter.hasNext()) {
	    assertEquals(treeIter.next(), listIter.next());

	}

    }
}
