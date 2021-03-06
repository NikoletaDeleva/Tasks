package com.egtinteractive.data_structures.list_tests;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.ThreadLocalRandom;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.data_structures.list.ArrayList;
import com.egtinteractive.data_structures.list.LinkedList;
import com.egtinteractive.data_structures.list.List;

public class ClearTest extends TestList{
    @DataProvider(name = "lists")
    public Object[][] createData() {
	return new Object[][] { { new ArrayList<>() }, { new LinkedList<>() }, };
    }

    @Test(dataProvider = "lists")
    public void clearAllElementsShouldReturZero(final List<Integer> list) {
	final int size = ThreadLocalRandom.current().nextInt(1,100);
	fillListWithIntegers(size, list);
	list.clear();

	final int expectedSize = 0;
	final int realSize = list.size();

	assertEquals(expectedSize, realSize);
    }
}