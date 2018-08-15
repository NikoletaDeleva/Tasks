package com.egtinteractive.data_structures.list_tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.data_structures.list.ArrayList;
import com.egtinteractive.data_structures.list.LinkedList;
import com.egtinteractive.data_structures.list.List;

public class ListAddTest extends TestList {

    @DataProvider(name = "lists")
    public Object[][] createData() {
	return new Object[][] { { new ArrayList<>() }, { new LinkedList<>() }, };
    }

    @Test(dataProvider = "lists")
    public void addByElementAtLastPositionAndSizeIncrease(final List<Integer> list) {
	final int size = ThreadLocalRandom.current().nextInt(1, 100);

	fillListWithIntegers(size, list);

	final int expectedSize = list.size() + 1;
	final int expectedFinalElement = ThreadLocalRandom.current().nextInt();

	list.add(expectedFinalElement);

	final int realSize = list.size();
	final int realFinalElement = list.get(list.size() - 1);

	assertEquals(expectedSize, realSize);
	assertEquals(expectedFinalElement, realFinalElement);

    }

    @Test(dataProvider = "lists")
    public void addNullValue(List<Integer> list) {
	list.add(ThreadLocalRandom.current().nextInt());
	list.add(null);
	list.add(ThreadLocalRandom.current().nextInt());

	assertTrue(list.contains(null));
    }

    @Test(dataProvider = "lists")
    public void addStringAtLastPositionAndSizeIncrease(final List<String> list) {
	final int size = ThreadLocalRandom.current().nextInt(1, 100);

	fillListWithStrings(size, list);

	final int expectedSize = list.size() + 1;
	final String expectedFinalElement = UUID.randomUUID().toString();

	list.add(expectedFinalElement);

	final int realSize = list.size();
	final String realFinalElement = list.get(list.size() - 1);

	assertEquals(expectedSize, realSize);
	assertEquals(expectedFinalElement, realFinalElement);
    }

    @Test(dataProvider = "lists")
    public void addNullStringValue(final List<String> list) {
	list.add(UUID.randomUUID().toString());
	list.add(null);
	list.add(UUID.randomUUID().toString());
	assertNull(list.get(1));
    }

    @Test(dataProvider = "lists")
    public void addHeadElementShouldReturnTrue(final List<Integer> list) {
	final int element = ThreadLocalRandom.current().nextInt();
	list.add(element);

	final int expectedElement = element;
	final int realElement = list.get(0);

	assertEquals(1, list.size());
	assertEquals(expectedElement, realElement);
    }

}
