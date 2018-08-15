package com.egtinteractive.data_structures.binary_tree_tests;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

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
	final int size = ThreadLocalRandom.current().nextInt(1,300);
	List<Integer> list = new ArrayList<>();

	for (int i = 0; i < size; i++) {
	    final int num = ThreadLocalRandom.current().nextInt(150);
	    tree.add(num);
	    list.add(num);
	}
	list = list.stream().distinct().sorted().collect(Collectors.toList());

	for (int index = tree.size()-1; index >=0; index--) {
	    final int treeResult = tree.pollLast();
	    final int listResult = list.remove(index);
	    assertEquals(treeResult, listResult);
	}
    }

    @Test(dataProvider = "trees")
    public void binaryTreePollLastSecond(final BinaryTree<Integer> tree) {
	final int size = ThreadLocalRandom.current().nextInt(1,300);
	List<Integer> list = new ArrayList<>();
	for (int i = 0; i < size; i++) {
	    final int num = ThreadLocalRandom.current().nextInt(150);
	    tree.add(num);
	    list.add(num);
	}
	list = list.stream().distinct().collect(Collectors.toList());

	for (int index = tree.size()-1; index >=0; index--) {
	    tree.pollLast();
	    list.remove(index);

	}
	assertEquals(tree.size(), list.size());
    }
   
}
