package com.egtinteractive.data_structures.binary_tree_tests;

import java.util.concurrent.ThreadLocalRandom;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.data_structures.binary_tree.BinaryTree;

public class ClearTest {
    @DataProvider(name = "trees")
    public Object[][] createData() {
	return new Object[][] { { new BinaryTree<>() } };
    }

    @Test(dataProvider = "trees")
    public void clearAndSizeTest(final BinaryTree<Integer> tree) {
	int someSize = ThreadLocalRandom.current().nextInt(1, 20);
	for (int i = 0; i < someSize; i++) {
	    tree.add(ThreadLocalRandom.current().nextInt(1, 10));
	}
	tree.clear();
	int newSize = tree.size();
	Assert.assertNotEquals(someSize, newSize);

    }
}
