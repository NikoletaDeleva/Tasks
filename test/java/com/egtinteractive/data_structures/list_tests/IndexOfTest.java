package com.egtinteractive.data_structures.list_tests;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.ThreadLocalRandom;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.data_structures.list.ArrayList;
import com.egtinteractive.data_structures.list.LinkedList;
import com.egtinteractive.data_structures.list.List;

public class IndexOfTest extends TestList {
    @DataProvider(name = "lists")
    public Object[][] createData() {
	return new Object[][] { { new ArrayList<>() }, { new LinkedList<>() }, };
    }

    @Test(dataProvider = "lists")
    public void returnTheIndexOfFirst(final List<Integer> list) {
	final int size = ThreadLocalRandom.current().nextInt(50,300);

	fillListWithIntegers(size, list);

	list.set(1, 30);
	list.set(30, 30);

	final int actualPosition = list.indexOf(30);
	final int expectedPosition = 1;

	assertEquals(actualPosition, expectedPosition);

    }

    @Test(dataProvider = "lists")
    public void indexOfNull(final List<Integer> list) {
	final int size = ThreadLocalRandom.current().nextInt(1,100);

	fillListWithIntegers(size, list);

	list.add(null);

	final int expectedPosition = size;
	final int realPosition = list.indexOf(null);

	assertEquals(expectedPosition, realPosition);
    }

    @Test(dataProvider = "lists")
    public void indexOfNonExistingElement(final List<Integer> list) {
	final int size = ThreadLocalRandom.current().nextInt(0,100);

	fillListWithIntegers(size, list);
       //Should change it 
	final int nonExistingElement = 100000;

	final int realIndex = list.indexOf(nonExistingElement);
	final int expectedIndex = -1;

	assertEquals(realIndex, expectedIndex);

    }
}
