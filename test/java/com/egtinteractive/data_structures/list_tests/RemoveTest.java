package com.egtinteractive.data_structures.list_tests;

import static org.junit.Assert.assertFalse;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.data_structures.list.ArrayList;
import com.egtinteractive.data_structures.list.LinkedList;
import com.egtinteractive.data_structures.list.List;

public class RemoveTest extends TestList {
    @DataProvider(name = "lists")
    public Object[][] createData() {
	return new Object[][] { { new ArrayList<>() }, { new LinkedList<>() }, };
    }

    @Test(dataProvider = "lists")
    public void removeElelmentAndSizeDecrase(final List<Integer> list) {
	final int size = ThreadLocalRandom.current().nextInt(1, 20);

	fillListWithIntegers(size, list);

	final int sizeBeforeRemoveElement = list.size();

	Integer getElement = list.get(ThreadLocalRandom.current().nextInt(0, size));
	boolean isTrue = list.remove(getElement);
	assertTrue(isTrue);

	final int sizeAfterRemoving = list.size();
	final int expectedSize = sizeBeforeRemoveElement - 1;

	assertEquals(sizeAfterRemoving, expectedSize);
    }

    @Test(dataProvider = "lists")
    public void removeElelmentSecond(final List<Integer> list) {
	final int size = ThreadLocalRandom.current().nextInt(1, 100);

	fillListWithIntegers(size, list);

	final int randInt = ThreadLocalRandom.current().nextInt(1000, 2000);
	list.add(randInt);

	assertTrue(list.remove(list.size() - 1));

	assertFalse(list.contains(randInt));

    }

    @Test(dataProvider = "lists")
    public void removeElelmentThird(final List<Integer> list) {
	final int size = ThreadLocalRandom.current().nextInt(10, 100);
	final int firstElement = ThreadLocalRandom.current().nextInt(1, 100);
	final int secondElement = ThreadLocalRandom.current().nextInt(100, 200);

	fillListWithIntegers(size, list);

	list.set(0, firstElement);
	list.set(1, secondElement);

	assertTrue(list.remove(0));
	int newFirst = list.get(0);
	assertEquals(secondElement, newFirst);

    }

    @Test(dataProvider = "lists")
    public void removeElelment(final List<Integer> list) {
	final int size = ThreadLocalRandom.current().nextInt(10, 100);
	fillListWithIntegers(size, list);

	Iterator<Integer> iterator = list.iterator();

	while (iterator.hasNext()) {
	    iterator.next();
	    iterator.remove();
	}

	assertEquals(list.size(), 0);
    }
}
