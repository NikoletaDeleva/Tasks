package com.egtinteractive.data_structures.binary_tree_tests;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

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
	final int size = ThreadLocalRandom.current().nextInt(1,100);
	List<Integer> list = new ArrayList<>();
	for (int index = 0; index < size; index++) {
	    final int num = ThreadLocalRandom.current().nextInt(1,150);
	    tree.add(num);
	    list.add(num);
	}
	
	list = list.stream().distinct().collect(Collectors.toList());

	for (int index = 0; index < tree.size(); index++) {
	    tree.pollFirst();
	    list.remove(0);

	}
	assertEquals(tree.size(), list.size());

    }
}
