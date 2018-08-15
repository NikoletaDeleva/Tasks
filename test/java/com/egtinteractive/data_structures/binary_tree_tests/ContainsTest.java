package com.egtinteractive.data_structures.binary_tree_tests;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.data_structures.binary_tree.BinaryTree;

public class ContainsTest {
    @DataProvider(name = "trees")
    public Object[][] createData() {
	return new Object[][] { { new BinaryTree<>() } };
    }

    @Test(dataProvider = "trees")
    public void containsTest(final BinaryTree<Integer> tree) {

	final int randSize = ThreadLocalRandom.current().nextInt(1, 100);
	final int randElement = ThreadLocalRandom.current().nextInt(1000, 2000);

	for (int index = 0; index < randSize; index++) {
	    final int randData = ThreadLocalRandom.current().nextInt(1, 1000);
	    tree.add(randData);
	}

	tree.add(randElement);

	assertTrue(tree.contains(randElement));

    }

    @Test(dataProvider = "trees")
    public void containsTestTwo(final BinaryTree<String> tree) {

	final int randSize = ThreadLocalRandom.current().nextInt(1, 100);
	final String randElement = UUID.randomUUID().toString();

	for (int index = 0; index < randSize; index++) {
	    final String randData = UUID.randomUUID().toString();
	    tree.add(randData);
	}

	tree.add(randElement);

	assertTrue(tree.contains(randElement));

    }

    @Test(dataProvider = "trees")
    public void containsTestThree(final BinaryTree<String> tree) {

	final int randSize = ThreadLocalRandom.current().nextInt(1, 100);
	final String randElement = UUID.randomUUID().toString();

	for (int index = 0; index < randSize; index++) {
	    final String randData = UUID.randomUUID().toString();
	    tree.add(randData);
	}

	assertFalse(tree.contains(randElement));

    }

    @Test(dataProvider = "trees")
    public void containsTestFour(final BinaryTree<String> tree) {
	final int randSize = ThreadLocalRandom.current().nextInt(1, 100);

	for (int index = 0; index < randSize; index++) {
	    final String randData = UUID.randomUUID().toString();
	    tree.add(randData);
	}
	
	assertFalse(tree.contains(null));
    }
}
