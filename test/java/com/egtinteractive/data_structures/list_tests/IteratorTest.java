package com.egtinteractive.data_structures.list_tests;

import static org.junit.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

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
	for (int i = 0; i < size; i++) {
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

}
