package com.egtinteractive.data_structures.binary_tree_tests;

import org.testng.annotations.Test;

import com.egtinteractive.data_structures.binary_tree.BinaryTree;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.ThreadLocalRandom;

import org.testng.annotations.DataProvider;

public class LowerTest {
    @DataProvider(name = "trees")
    public Object[][] createData() {
	return new Object[][] { { new BinaryTree<>() } };
    }

    @Test(dataProvider = "trees")
    public void emptyTree(final BinaryTree<Integer> tree) {
	final Integer expected = null;

	final Integer actual = tree.lower(ThreadLocalRandom.current().nextInt(1,100));

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
	final int expectedLower = element - 1;

	tree.add(element);
	tree.add(expectedLower);

	final int realLower = tree.lower(element);

	assertEquals(expectedLower, realLower);

    }
}
