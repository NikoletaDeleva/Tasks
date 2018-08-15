package com.egtinteractive.data_structures.binary_tree_tests;

import java.util.concurrent.ThreadLocalRandom;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.data_structures.binary_tree.BinaryTree;

public class EqualsTest {
    @DataProvider(name = "trees")
    public Object[][] createData() {
	return new Object[][] { { new BinaryTree<>(), new BinaryTree<>() } };
    }

    @Test(dataProvider = "trees")
    public void equalsShouldReturnTrueForTreesWithSameContent(final BinaryTree<Integer> tree1,
	    final BinaryTree<Integer> tree2) {
	final int size = ThreadLocalRandom.current().nextInt(100, 300);
	for (int index = 0; index < size; index++) {
	    final int num = ThreadLocalRandom.current().nextInt(3000);
	    tree1.add(num);
	    tree2.add(num);
	}
	final boolean result1 = tree1.equals(tree2);
	final boolean result2 = tree2.equals(tree1);
	Assert.assertTrue(result1);
	Assert.assertTrue(result2);
    }

    @Test(dataProvider = "trees")
    public void equalsShouldReturnFalseForTreesWithDifferentContent(final BinaryTree<Integer> tree1,
	    final BinaryTree<Integer> tree2) {
	final int size = ThreadLocalRandom.current().nextInt(100, 300);
	for (int index = 0; index < size; index++) {
	    final int num1 = ThreadLocalRandom.current().nextInt(3000);
	    final int num2 = ThreadLocalRandom.current().nextInt(3000);
	    tree1.add(num1);
	    tree2.add(num2);
	}
	final boolean result1 = tree1.equals(tree2);
	final boolean result2 = tree2.equals(tree1);
	Assert.assertFalse(result1);
	Assert.assertFalse(result2);

    }

    @Test(dataProvider = "trees")
    public void equalsShouldReturnFalseWithSlightDifferenceInContent(final BinaryTree<Integer> tree1,
	    final BinaryTree<Integer> tree2) {
	final int size = ThreadLocalRandom.current().nextInt(100, 300);
	for (int index = 0; index < size; index++) {
	    final int num = ThreadLocalRandom.current().nextInt(3000);
	    tree1.add(num);
	    tree2.add(num);
	}
	tree2.add(30000);

	final boolean result1 = tree1.equals(tree2);
	final boolean result2 = tree2.equals(tree1);
	Assert.assertFalse(result1);
	Assert.assertFalse(result2);
    }
}
