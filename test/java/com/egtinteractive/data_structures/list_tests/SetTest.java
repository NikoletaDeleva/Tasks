package com.egtinteractive.data_structures.list_tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import java.util.concurrent.ThreadLocalRandom;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.data_structures.list.ArrayList;
import com.egtinteractive.data_structures.list.LinkedList;
import com.egtinteractive.data_structures.list.List;

public class SetTest extends TestList {
    @DataProvider(name = "lists")
    public Object[][] createData() {
	return new Object[][] { { new ArrayList<>() }, { new LinkedList<>() }, };
    }

    @Test(dataProvider = "lists")
    public void changeElementCheckShouldReturnTrue(final List<Integer> list) {
	final int size = ThreadLocalRandom.current().nextInt(1,100);

	fillListWithIntegers(size, list);

	final int randIndex = ThreadLocalRandom.current().nextInt(0,size);
	final int expectedElement = ThreadLocalRandom.current().nextInt();

	list.set(randIndex, expectedElement);

	final int realElement = list.get(randIndex);

	assertEquals(expectedElement, realElement);
    }

    @Test(dataProvider = "lists")
    public void NullSetCheckShouldReturnTrue(final List<Integer> list) {
	final int size = ThreadLocalRandom.current().nextInt(1,100);

	fillListWithIntegers(size, list);

	final int randIndex = ThreadLocalRandom.current().nextInt(0, size);

	list.set(randIndex, null);

	assertNull(list.get(randIndex));
    }

    @Test(dataProvider = "lists")
    public void indexOfSetElementCheckShouldReturnTrue(final List<Integer> list) {
	final int size = ThreadLocalRandom.current().nextInt(1,100);

	fillListWithIntegers(size, list);

	final int randElement = ThreadLocalRandom.current().nextInt();
	final int indexToSetOn = ThreadLocalRandom.current().nextInt(0, size);
	list.set(indexToSetOn, randElement);

	final int realPosition = list.indexOf(randElement);

	assertEquals(indexToSetOn, realPosition);
    }
    
    public void setAtLastPositionSizeDoesNotChange(final List<Integer> list) {
	final int size = ThreadLocalRandom.current().nextInt(1,100);
	
	fillListWithIntegers(size, list);
	
	final int randElement = ThreadLocalRandom.current().nextInt();
	
	list.set(size-1, randElement);
	
	final int realSize = list.size();
	
	assertEquals(realSize, size);
    }
}
