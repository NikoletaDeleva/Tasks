package com.egtinteractive.data_structures.list_tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import java.util.concurrent.ThreadLocalRandom;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.data_structures.list.ArrayList;
import com.egtinteractive.data_structures.list.LinkedList;
import com.egtinteractive.data_structures.list.List;

public class GetTest extends TestList {
    @DataProvider(name = "lists")
    public Object[][] createData() {
	return new Object[][] { { new ArrayList<>() }, { new LinkedList<>() }, };
    }

    @Test(dataProvider = "lists")
    public void getElementByIndexTest(final List<Integer> list) {
	final int one = ThreadLocalRandom.current().nextInt();
	final int two = ThreadLocalRandom.current().nextInt();
	final int three = ThreadLocalRandom.current().nextInt();

	list.add(one);
	list.add(two);
	list.add(three);

	final int result = list.get(1);
	assertEquals(result, two);

    }

    @Test(dataProvider = "lists")
    public void getNullByIndex(final List<Integer> list) {
	final int size = ThreadLocalRandom.current().nextInt(1, 100);

	fillListWithIntegers(size, list);

	final int randPosition = ThreadLocalRandom.current().nextInt(0, size);
	list.set(randPosition, null);

	assertNull(list.get(randPosition));
    }

    @Test(dataProvider = "lists")
    public void getWithLotOfElements(final List<Integer> list) {
	final int size = ThreadLocalRandom.current().nextInt(1, 100);
	final Integer[] array = new Integer[size];

	for (int index = 0; index < size; index++) {
	    final int num = ThreadLocalRandom.current().nextInt();
	    list.add(num);
	    array[index] = num;
	}

	for (int index = 0; index < size; index++) {
	    
	   assertEquals(list.get(index), array[index]);
	}
    }
}
