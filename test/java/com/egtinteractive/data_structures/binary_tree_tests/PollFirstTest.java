package com.egtinteractive.data_structures.binary_tree_tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.data_structures.binary_tree.BinaryTree;

public class PollFirstTest {
    @DataProvider(name = "trees")
    public Object[][] createData() {
	return new Object[][] { { new BinaryTree<>() } };
    }

    @Test(dataProvider = "trees")
    public void binaryTreePollFirstTest(final BinaryTree<Integer> tree) {
	int randNumb = ThreadLocalRandom.current().nextInt(1, 20);
	int lower = ThreadLocalRandom.current().nextInt(randNumb);

	tree.add(randNumb);
	tree.add(lower);
	Comparable<Integer> realNumber = tree.pollFirst();
	assertEquals(lower, realNumber);

    }

    @Test(dataProvider = "trees")
    public void binaryTreePollSecondTest(final BinaryTree<Integer> tree) {
	int size = ThreadLocalRandom.current().nextInt(1, 20);
	List<Integer> list = new ArrayList<>();
	for (int i = 0; i < size; i++) {
	    tree.add(ThreadLocalRandom.current().nextInt(1, 10));
	}
	tree.pollFirst();
	assertNotEquals(list.size(), tree.size());

    }
}
