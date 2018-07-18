package com.egtinteractive.data_structures.binary_tree_tests;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.ThreadLocalRandom;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.data_structures.binary_tree.BinaryTree;

public class HigherTest {
    @DataProvider(name = "trees")
    public Object[][] createData() {
	return new Object[][] { { new BinaryTree<>() } };
    }

    @Test(dataProvider = "trees")
    public void emptyTree(final BinaryTree<Integer> tree) {
	final Object expected = null;

	final Object actual = tree.higher(ThreadLocalRandom.current().nextInt());

	assertEquals(expected, actual);
    }

    @Test(dataProvider = "trees")
    public void lowerThanShouldReturnTrue(final BinaryTree<Integer> tree) {

	final int randSize = ThreadLocalRandom.current().nextInt(1,100);

	for (int index = 0; index < randSize; index++) {
	    final int randData = ThreadLocalRandom.current().nextInt();
	    tree.add(randData);
	}

	final int element = ThreadLocalRandom.current().nextInt();
	final int expectedHigher = element + 1;

	tree.add(element);
	tree.add(expectedHigher);

	final int realHigher = tree.higher(element);

	assertEquals(expectedHigher, realHigher);

    }
}
