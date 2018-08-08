package com.egtinteractive.data_structures.binary_tree_tests;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.ThreadLocalRandom;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.data_structures.binary_tree.BinaryTree;

public class PollLastTest {
    @DataProvider(name = "trees")
    public Object[][] createData() {
	return new Object[][] { { new BinaryTree<>() } };
    }

    @Test(dataProvider = "trees")
    public void binaryTreePollLastFirst(final BinaryTree<Integer> tree) {
	int randNumb = ThreadLocalRandom.current().nextInt(1, 20);
	int lower = ThreadLocalRandom.current().nextInt(randNumb);

	tree.add(randNumb);
	tree.add(lower);
	Comparable<Integer> realNumber = tree.pollFirst();
	assertEquals(lower, realNumber);
    }

   
}
