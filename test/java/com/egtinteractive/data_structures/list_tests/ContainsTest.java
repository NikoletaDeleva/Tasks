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

public class ContainsTest extends TestList{

    @DataProvider(name = "lists")
    public Object[][] createData() {
	return new Object[][] { { new ArrayList<>() }, { new LinkedList<>() }, };
    }

    @Test(dataProvider = "lists")
    public void containsTheValueShouldReturnTrue(final List<Integer> list) {
	final int size = ThreadLocalRandom.current().nextInt(1,100);
	
	fillListWithIntegers(size, list);

	final int existing = ThreadLocalRandom.current().nextInt();
	list.add(existing);
	assertTrue(list.contains(existing));
    }

    @Test(dataProvider = "lists")
    public void doesNotContainsValueShouldReturnFalse(final List<Integer> list) {
	final int size = ThreadLocalRandom.current().nextInt(1,100);
	
	fillListWithIntegers(size, list);
	
	final int notExisting = ThreadLocalRandom.current().nextInt(500,1000);
	assertFalse(list.contains(notExisting));
    }

    @Test(dataProvider = "lists")
    public void containsTheStringShouldReturnTrue(final List<String> list) {
	final int size = ThreadLocalRandom.current().nextInt(1,100);
	
	fillListWithStrings(size, list);

	final String existing = UUID.randomUUID().toString();
	list.add(existing);
	
	assertTrue(list.contains(existing));
    }

    @Test(dataProvider = "lists")
    public void containsNullShouldReturnTrue(final List<Integer> list) {
	list.add(null);
	assertTrue(list.contains(null));
    }

    @Test(dataProvider = "lists")
    public void doesNotcontainsNullShouldReturnTrue(final List<Integer> list) {
	final int size = ThreadLocalRandom.current().nextInt(1,100);
	
	fillListWithIntegers(size, list);
	
	assertTrue(!list.contains(null));
    }

}
