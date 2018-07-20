package com.egtinteractive.data_structures.binary_tree_tests;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.ThreadLocalRandom;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.data_structures.binary_tree.BinaryTree;

public class SizeTest {
    @DataProvider(name = "trees")
    public Object[][] createData() {
	return new Object[][] { { new BinaryTree<>() } };
    }

    @Test(dataProvider = "trees")
    public void sizeTest(final BinaryTree<Integer> tree) {
	final int size = ThreadLocalRandom.current().nextInt(1,100);
	
	for(int index = 0; index< size; index++) {
	    tree.add(ThreadLocalRandom.current().nextInt());
	}
	
	assertEquals(size, tree.size());
    }
}
