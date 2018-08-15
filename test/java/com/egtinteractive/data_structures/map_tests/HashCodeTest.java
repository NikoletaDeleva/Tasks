package com.egtinteractive.data_structures.map_tests;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.ThreadLocalRandom;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.data_structures.map.HashMap;
import com.egtinteractive.data_structures.map.Map;

public class HashCodeTest {
    @DataProvider(name = "maps")
    public Object[][] createData() {
	return new Object[][] { { new HashMap<>(), new HashMap<>() } };
    }

    @Test(dataProvider = "maps")
    public void hashCodeOne(Map<String, Integer> map1, Map<String, Integer> map2) {
	assertEquals(map1.hashCode(), map2.hashCode());

    }

    @Test(dataProvider = "maps")
    public void hashCodeTwo(Map<String, Integer> map1, Map<String, Integer> map2) {
	final int size = ThreadLocalRandom.current().nextInt(100, 1000);
	for (int index = 1; index < size; index++) {
	    map1.put(String.valueOf(index), index);
	    map2.put(String.valueOf(index), index);
	}

	final int result1 = map1.hashCode();
	final int result2 = map2.hashCode();
	assertEquals(result1, result2);

    }

    @Test(dataProvider = "maps")
    public void hashCodeSix(Map<String, Integer> map1, Map<String, Integer> map2) {
	final int size = ThreadLocalRandom.current().nextInt(100, 1000);
	for (int index = 1; index < size; index++) {
	    map1.put(String.valueOf(index), null);
	    map2.put(String.valueOf(index), null);
	}

	final int result1 = map1.hashCode();
	final int result2 = map2.hashCode();
	assertEquals(result1, result2);

    }
}
