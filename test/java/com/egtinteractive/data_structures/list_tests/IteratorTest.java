package com.egtinteractive.data_structures.list_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.data_structures.list.ArrayList;
import com.egtinteractive.data_structures.list.LinkedList;
import com.egtinteractive.data_structures.list.List;

public class IteratorTest extends TestList {
    @DataProvider(name = "lists")
    public Object[][] createData() {
	return new Object[][] { { new ArrayList<>() }, { new LinkedList<>() }, };
    }

    @Test(dataProvider = "lists")
    public void testIteratorAfterClear(final List<String> list) {
	int size = ThreadLocalRandom.current().nextInt(1, 100);
	for (int index = 0; index < size; index++) {
	    list.add(UUID.randomUUID().toString());
	}
	list.clear();
	assertFalse(list.iterator().hasNext());
    }

    @Test(dataProvider = "lists")
    public void iteratorTest(List<String> list) {
	int size = ThreadLocalRandom.current().nextInt(1, 100);
	fillListWithStrings(size, list);
	assertTrue(list.iterator().hasNext());
    }

    @Test(dataProvider = "lists")
    public void iteratorFour(List<Integer> list) {
	final int size = ThreadLocalRandom.current().nextInt(100, 1000);
	int[] arr = new int[size];
	for (int index = 0; index < size; index++) {
	    final int num = ThreadLocalRandom.current().nextInt();
	    list.add(num);
	    arr[index] = num;
	}

	int[] resultArray = new int[size];
	int count = 0;
	for (int index : list) {
	    resultArray[count++] = index;
	}

	for (int index = 0; index < size; index++) {
	    assertEquals(resultArray[index], arr[index]);
	}
    }

    @Test(dataProvider = "lists")
    public void iteratorThree(List<Integer> list) {

	int result = 0;
	for (int index : list) {
	    result += index;
	}

	Assert.assertEquals(result, 0);

    }

    @Test(dataProvider = "lists")
    public void iteratorTwo(List<Integer> list) {
	final int size = ThreadLocalRandom.current().nextInt(100, 1000);
	for (int index = 0; index < size; index++) {
	    final int num = ThreadLocalRandom.current().nextInt();
	    list.add(num);
	}
	final int expectedResult = list.get(1);

	Iterator<Integer> iterator = list.iterator();

	iterator.next();
	iterator.remove();
	final int actualResult = list.get(0);

	Assert.assertEquals(actualResult, expectedResult);

    }

    @Test(dataProvider = "lists")
    public void iteratorOne(List<Integer> list) {
	for (int index = 0; index < 10; index++) {
	    list.add(index);
	}
	Iterator<Integer> iterator = list.iterator();
	while (iterator.hasNext()) {
	    iterator.next();
	    iterator.remove();
	}
	Assert.assertEquals(list.size(), 0);

    }

}
