package com.egtinteractive.data_structures.list_tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.data_structures.list.ArrayList;
import com.egtinteractive.data_structures.list.LinkedList;
import com.egtinteractive.data_structures.list.List;

public class ListAddByIndexTest extends TestList{

    @DataProvider(name = "lists")
    public Object[][] createData() {
	return new Object[][] { { new ArrayList<>() }, { new LinkedList<>() }, };
    }

    @Test(dataProvider = "lists")
    public void addIntegerValueInLastPositionAndSizeIncrease(final List<Integer> list) {
	final int size = ThreadLocalRandom.current().nextInt(1,100);

	fillListWithIntegers(size, list);

	final int expectedSize = list.size() + 1;

	final int randomElement = ThreadLocalRandom.current().nextInt();
	final int randomIndex = ThreadLocalRandom.current().nextInt(0,size);

	list.add(randomIndex, randomElement);

	final int realSize = list.size();
	final int realElement = list.get(randomIndex);

	assertEquals(expectedSize, realSize);
	assertEquals(realElement, randomElement);
    }

    @Test(dataProvider = "lists")
    public void addStringValueInLastPositionAndSizeIncrease(final List<String> list) {
	final int size = ThreadLocalRandom.current().nextInt(1,100);

	fillListWithStrings(size, list);
	
	final int expectedSize = list.size() + 1;

	final String randomElement = UUID.randomUUID().toString();
	final int randomIndex = ThreadLocalRandom.current().nextInt(0, size);

	list.add(randomIndex, randomElement);

	final int realSize = list.size();
	final String realElement = list.get(randomIndex);

	assertEquals(expectedSize, realSize);
	assertEquals(realElement, randomElement);

    }

    @Test(dataProvider = "lists")
    public void addNullValueShouldContainsNull(final List<Integer> list) {
	list.add(ThreadLocalRandom.current().nextInt());
	list.add(ThreadLocalRandom.current().nextInt());
	list.add(1, null);

	assertTrue(list.contains(null));
    }

    @Test(dataProvider = "lists")
    public void addNullStringShouldContainsNull(final List<String> list) {
	list.add(UUID.randomUUID().toString());
	list.add(UUID.randomUUID().toString());
	list.add(1, null);

	assertTrue(list.contains(null));
    }

}
