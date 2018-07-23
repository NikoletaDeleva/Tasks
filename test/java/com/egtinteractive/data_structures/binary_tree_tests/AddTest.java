package com.egtinteractive.data_structures.binary_tree_tests;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.ThreadLocalRandom;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.data_structures.binary_tree.BinaryTree;

public class AddTest {
    @DataProvider(name = "trees")
    public Object[][] createData() {
	return new Object[][] { { new BinaryTree<>() } };
    }

    @Test(dataProvider = "trees")
    public void addOneElement(final BinaryTree<Integer> tree) {
	final int expectedSize = 1;

	final int randData = ThreadLocalRandom.current().nextInt();
	tree.add(randData);

	final int realSize = tree.size();

	assertEquals(expectedSize, realSize);
    }


    @Test(dataProvider = "trees") 
    public void addShouldNotIncreaseSizeIfItemIsAlreadyPresent(final BinaryTree<Integer> tree) {
	
	final int size = ThreadLocalRandom.current().nextInt(1, 100);
	for (int i = 0; i < size; i++) {
	    tree.add(i);
	}
	final int treeExpectedSize = tree.size();

	for (int i = 0; i < size; i++) {
	    tree.add(i);
	}
	final int treeActualSize = tree.size();

	assertEquals(treeActualSize, treeExpectedSize);

    }

}
