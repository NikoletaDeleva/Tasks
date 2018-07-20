package com.egtinteractive.data_structures.list_tests;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.data_structures.list.ArrayList;
import com.egtinteractive.data_structures.list.LinkedList;
import com.egtinteractive.data_structures.list.List;

public class EqualsTest extends TestList {
    @DataProvider(name = "lists")
    public Object[][] createData() {
	return new Object[][] { { new ArrayList<>(), new ArrayList<>() }, { new LinkedList<>(), new LinkedList<>() } };
    }

    @Test(dataProvider = "lists")
    public void shouldReturnTrueIfTwoListsAreEmpty(final List<Integer> listFirst, final List<Integer> listSecond) {
	final boolean result = listFirst.equals(listSecond);
	Assert.assertTrue(result);
	final boolean result2 = listSecond.equals(listFirst);
	Assert.assertTrue(result2);
    }

    @Test(dataProvider = "lists")
    public void equalsShouldReturnTrueIfTwoListsHaveTheSameValues(final List<String> listFirst,
	    final List<String> listSecond) {
	int size = ThreadLocalRandom.current().nextInt(1, 100);

	for (int i = 0; i < size; i++) {
	    final String value = UUID.randomUUID().toString();
	    listFirst.add(String.valueOf(value));
	    listSecond.add(String.valueOf(value));
	}

	final boolean result = listFirst.equals(listSecond);
	Assert.assertTrue(result);
	final boolean result2 = listSecond.equals(listFirst);
	Assert.assertTrue(result2);
    }
}
