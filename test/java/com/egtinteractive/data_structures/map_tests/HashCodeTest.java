package com.egtinteractive.data_structures.map_tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import java.util.concurrent.ThreadLocalRandom;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.data_structures.map.HashMap;
import com.egtinteractive.data_structures.map.Map;

public class HashCodeTest {
    @DataProvider(name = "maps")
    public Object[][] createData() {
	return new Object[][] { { new HashMap<>() , new HashMap<>()  } };
    }

    @Test(dataProvider = "maps")
    public void hashCodeOne(Map<String, Integer> map1, Map<String, Integer> map2) {
	assertEquals(map1.hashCode(), map2.hashCode());

    }

    @Test(dataProvider = "maps")
    public void hashCodeTwo(Map<String, Integer> map1,
	    Map<String, Integer> map2) {
	final int size = ThreadLocalRandom.current().nextInt(100,1000);
	for (int i = 1; i < size; i++) {
	    map1.put(String.valueOf(i), i);
	    map2.put(String.valueOf(i), i);
	}

	final int result1 = map1.hashCode();
	final int result2 = map2.hashCode();
	assertEquals(result1, result2);

    }

    @Test(dataProvider = "maps")
    public void hashCodeThree(Map<String, Integer> map1,
	    Map<String, Integer> map2) {
	final int size = ThreadLocalRandom.current().nextInt(100,1000);
	for (int i = 1; i < size; i++) {
	    final int num1 = ThreadLocalRandom.current().nextInt();
	    final int num2 = ThreadLocalRandom.current().nextInt();
	    map1.put(String.valueOf(i), num1);
	    map2.put(String.valueOf(i), num2);
	}

	map1.put("ABC", null);
	map2.put("ABC", null);

	final int result1 = map1.hashCode();
	final int result2 = map2.hashCode();
	assertNotEquals(result1, result2);

    }

    @Test(dataProvider = "maps")
    public void hashCodeFour(Map<String, Integer> map1,
	    Map<String, Integer> map2) {
	final int size = ThreadLocalRandom.current().nextInt(100,1000);
	for (int i = 1; i < size; i++) {
	    final int num1 = ThreadLocalRandom.current().nextInt();
	    final int num2 = ThreadLocalRandom.current().nextInt();
	    map1.put(String.valueOf(num1), i);
	    map2.put(String.valueOf(num2), i);
	}

	map1.put("ABC", null);
	map2.put("ABC", null);

	final int result1 = map1.hashCode();
	final int result2 = map2.hashCode();
	assertNotEquals(result1, result2);

    }

    @Test(dataProvider = "maps")
    public void hashCodesFive(Map<String, Integer> map1,
	    Map<String, Integer> map2) {
	
	final int size = ThreadLocalRandom.current().nextInt(100,1000);
	for (int i = 1; i < size; i++) {
	    map1.put(String.valueOf(i), i);
	    map2.put(String.valueOf(i), i);
	}
	map1.put("ABC", 3123123);

	final int result1 = map1.hashCode();
	final int result2 = map2.hashCode();
	assertNotEquals(result1, result2);

    }

    @Test(dataProvider = "maps")
    public void hashCodeSix(Map<String, Integer> map1,
	    Map<String, Integer> map2) {
	final int size = ThreadLocalRandom.current().nextInt(100,1000);
	for (int i = 1; i < size; i++) {
	    map1.put(String.valueOf(i), null);
	    map2.put(String.valueOf(i), null);
	}

	final int result1 = map1.hashCode();
	final int result2 = map2.hashCode();
	assertEquals(result1, result2);

    }
}
