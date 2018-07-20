package com.egtinteractive.data_structures.list_tests;

import java.util.concurrent.ThreadLocalRandom;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.data_structures.list.ArrayList;
import com.egtinteractive.data_structures.list.LinkedList;
import com.egtinteractive.data_structures.list.List;

public class HashCodeTest extends TestList {
    @DataProvider(name = "lists")
    public Object[][] createData() {
	return new Object[][] { { new ArrayList<>(), new ArrayList<>() }, { new LinkedList<>(), new LinkedList<>() } };

    }

    @Test(dataProvider = "lists")
    public void hashCodeShouldReturnEqualResultForListsWithSameElements(List<String> listFirst, List<String> listSecond) {
	for (int i = 0; i < 100; i++) {
	    final int num = ThreadLocalRandom.current().nextInt();
	    listFirst.add(String.valueOf(num));
	    listSecond.add(String.valueOf(num));
	}

	final int index = ThreadLocalRandom.current().nextInt(1, 100);
	listFirst.add(index, null);
	listSecond.add(index, null);

	final int result = listFirst.hashCode();
	final int result2 = listSecond.hashCode();
	Assert.assertEquals(result, result2);
    }
    
    @Test(dataProvider = "lists")
    public void hashCodeShouldReturnDifferentResultForListsWithSlightlyDifferentElements(List<String> listFirst,
	    List<String> listSecond) {
	int size = ThreadLocalRandom.current().nextInt(1,100);
	
	fillListWithStrings(size, listFirst);
	fillListWithStrings(size, listSecond);
	
	final int result = listFirst.hashCode();
	final int result2 = listSecond.hashCode();
	Assert.assertNotEquals(result, result2);
    }
}
