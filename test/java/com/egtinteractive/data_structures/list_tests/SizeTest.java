package com.egtinteractive.data_structures.list_tests;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.ThreadLocalRandom;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.data_structures.list.ArrayList;
import com.egtinteractive.data_structures.list.LinkedList;
import com.egtinteractive.data_structures.list.List;

public class SizeTest extends TestList {
    @DataProvider(name = "lists")
    public Object[][] createData() {
	return new Object[][] { { new ArrayList<>() }, { new LinkedList<>() }, };
    }

    @Test(dataProvider = "lists")
    public void sizeTestShouldReturnTrue(final List<Integer> list) {
	final int size = ThreadLocalRandom.current().nextInt(1,100);

	fillListWithIntegers(size, list);

	final int realSize = list.size();
	final int expectedSize = size;

	assertTrue(realSize == expectedSize);
    }

    @Test(dataProvider = "lists")
    public void sizeIncreaseAfterAddingNullShouldReturnTrue(final List<Integer> list) {
	int size = ThreadLocalRandom.current().nextInt(1,100);

	fillListWithIntegers(size, list);

	list.add(null);
	size++;

	final int realSize = list.size();
	final int expectedSize = size;

	assertTrue(realSize == expectedSize);
    }

}
