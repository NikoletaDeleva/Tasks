package com.egtinteractive.data_structures.map_tests;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import java.util.concurrent.ThreadLocalRandom;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.data_structures.map.HashMap;
import com.egtinteractive.data_structures.map.Map;

public class RemoveTest {
    @DataProvider(name = "maps")
    public Object[][] createData() {
	return new Object[][] { { new HashMap<>() } };
    }

    @Test(dataProvider = "maps")
    public void removeOne(Map<Character, Integer> map) {
	for (int i = 1; i < 20; i++) {
	    map.put((char) i, i);
	}
	for (int i = 1; i < 20; i++) {
	    map.remove((char) i);
	    final boolean keyResult = map.containsKey((char) i);
	    final boolean valueResult = map.containsValue(i);
	    assertFalse(keyResult);
	    assertFalse(valueResult);
	}
	assertEquals(map.size(), 0);
    }

    @Test(dataProvider = "maps")
    public void removeTwo(Map<Character, Integer> map) {
	final int size = ThreadLocalRandom.current().nextInt(100, 1000);
	for (int i = 1; i < size; i++) {
	    map.put((char) i, i);
	}

	int mapSize = map.size();
	for (int i = 1; i < size; i++) {
	    map.remove((char) i);
	    assertEquals(map.size(), --mapSize);
	}
    }

    @Test(dataProvider = "maps")
    public void removeThree(Map<Character, Integer> map) {
	final int size = ThreadLocalRandom.current().nextInt(100, 1000);
	for (int i = 1; i < size; i++) {
	    map.put((char) i, i);
	}
	final Integer result = map.get((char) (size + 1));
	assertNull(result);
    }

    @Test(dataProvider = "maps")
    public void removeFour(Map<Character, Integer> map) {
	final int size = ThreadLocalRandom.current().nextInt(100, 1000);
	for (int i = 1; i < size; i++) {
	    map.put((char) i, i);
	}

	final int secondSize = ThreadLocalRandom.current().nextInt(200, 250);
	final int mapSize = map.size();
	for (int i = size; i < secondSize; i++) {
	    map.remove((char) i);
	    final int result = map.size();
	    assertEquals(result, mapSize);
	}
    }

    @Test(dataProvider = "maps")
    public void removeFive(Map<Character, Integer> map) {
	final int size = ThreadLocalRandom.current().nextInt(100, 1000);
	for (int i = 1; i <= size; i++) {
	    map.put((char) i, i);
	}

	for (int i = size; i >= 1; i--) {
	    final int result = map.remove((char) i);
	    assertEquals(result, i);
	}
    }

    @Test(dataProvider = "maps")
    public void removeSix(Map<Character, Integer> map) {
	final int num = ThreadLocalRandom.current().nextInt(150);
	map.put((char) num, num);
	final int result = map.remove((char) num);
	assertEquals(result, num);
	assertEquals(map.size(), 0);
    }

    @Test(dataProvider = "maps")
    public void removeSeven(Map<Character, Integer> map) {
	final int size = ThreadLocalRandom.current().nextInt(100, 1000);
	for (int i = 1; i < size; i++) {
	    map.put((char) i, i);
	}
	final int num = ThreadLocalRandom.current().nextInt(2000);
	map.put(null, num);
	final int result = map.remove(null);

	assertEquals(result, num);

    }

    @Test(dataProvider = "maps")
    public void removeEight(Map<String, Integer> map) {
	for (int i = 1; i < 10; i++) {
	    map.put(String.valueOf(i), i);
	}

	final int result = map.remove("9");

	assertEquals(result, 9);
	assertEquals(map.size(), 8);

    }

    @Test(dataProvider = "maps")
    public void removeNine(Map<Character, Integer> map) {
	for (int i = 0; i < 100; i += 16) {
	    map.put((char) i, i);
	}

	final int result = map.remove((char) 96);

	assertEquals(result, 96);

    }

}
