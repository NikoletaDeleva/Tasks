package com.egtinteractive.data_structures.binary_tree_tests;

import static org.junit.Assert.assertFalse;
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
	final int randSize = ThreadLocalRandom.current().nextInt(1,10);
	final int randNumb = ThreadLocalRandom.current().nextInt(0,100);
	
	for (int index = 0; index < randSize; index++) {
	    final int randData = ThreadLocalRandom.current().nextInt(0,100);
	    tree.add(randData);
	}
	
	
	
	System.out.println(tree.size());
	
	tree.add(randNumb);
	
	System.out.println(tree.size());
	
	boolean a = tree.remove(randNumb);
	System.out.println(a);
	System.out.println(tree.size());
	
	final int realSize = tree.size();
	System.out.println(realSize);
	
	assertEquals(randSize, realSize);
    }
    
    @Test(dataProvider = "trees")
    public void second(final BinaryTree<Integer> tree) {
	final int randSize = ThreadLocalRandom.current().nextInt(1,100);
	
	for (int index = 0; index < randSize; index++) {
	    final int randData = ThreadLocalRandom.current().nextInt(0,100);
	    tree.add(randData);
	}
	
	assertFalse(tree.remove(ThreadLocalRandom.current().nextInt(-100,0)));
    }
}
