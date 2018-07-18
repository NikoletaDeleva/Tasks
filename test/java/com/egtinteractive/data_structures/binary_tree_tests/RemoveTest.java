package com.egtinteractive.data_structures.binary_tree_tests;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.ThreadLocalRandom;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.data_structures.binary_tree.BinaryTree;

public class RemoveTest {
    @DataProvider(name = "trees")
    public Object[][] createData() {
	return new Object[][] { { new BinaryTree<>() } };
    }

    @Test(dataProvider = "trees")
    public void f(final BinaryTree<Integer> tree) {
	final int randSize = ThreadLocalRandom.current().nextInt(1,100);

	for (int index = 0; index < randSize; index++) {
	    final int randData = ThreadLocalRandom.current().nextInt();
	    tree.add(randData);
	}
	
	final int randNumb = ThreadLocalRandom.current().nextInt();
	
	System.out.println(tree.size());
	
	tree.add(randNumb);
	
	System.out.println(tree.size());
	
//	final int expectedSize = tree.size() - 1;
	
	tree.remove(randNumb);
	System.out.println(tree.size());
	
	final int realSize = tree.size();
	System.out.println(realSize);
	
	assertEquals(randSize, realSize);
    }
}
