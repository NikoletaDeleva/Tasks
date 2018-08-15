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
	for (int index = 1; index < 20; index++) {
	    map.put((char) index, index);
	}
	for (int index = 1; index < 20; index++) {
	    map.remove((char) index);
	    final boolean keyResult = map.containsKey((char) index);
	    final boolean valueResult = map.containsValue(index);
	    assertFalse(keyResult);
	    assertFalse(valueResult);
	}
	assertEquals(map.size(), 0);
    }

    @Test(dataProvider = "maps")
    public void removeTwo(Map<Character, Integer> map) {
	final int size = ThreadLocalRandom.current().nextInt(100, 1000);
	for (int index = 1; index < size; index++) {
	    map.put((char) index, index);
	}

	int mapSize = map.size();
	for (int index = 1; index < size; index++) {
	    map.remove((char) index);
	    assertEquals(map.size(), --mapSize);
	}
    }

    @Test(dataProvider = "maps")
    public void removeThree(Map<Character, Integer> map) {
	final int size = ThreadLocalRandom.current().nextInt(100, 1000);
	for (int index = 1; index < size; index++) {
	    map.put((char) index, index);
	}
	final Integer result = map.get((char) (size + 1));
	assertNull(result);
    }

    @Test(dataProvider = "maps")
    public void removeFour(Map<Character, Integer> map) {
	final int size = ThreadLocalRandom.current().nextInt(100, 1000);
	for (int index = 1; index < size; index++) {
	    map.put((char) index, index);
	}

	final int secondSize = ThreadLocalRandom.current().nextInt(200, 250);
	final int mapSize = map.size();
	for (int index = size; index < secondSize; index++) {
	    map.remove((char) index);
	    final int result = map.size();
	    assertEquals(result, mapSize);
	}
    }

    @Test(dataProvider = "maps")
    public void removeFive(Map<Character, Integer> map) {
	final int size = ThreadLocalRandom.current().nextInt(100, 1000);
	for (int index = 1; index <= size; index++) {
	    map.put((char) index, index);
	}

	for (int index = size; index >= 1; index--) {
	    final int result = map.remove((char) index);
	    assertEquals(result, index);
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
	for (int index = 1; index < size; index++) {
	    map.put((char) index, index);
	}
	final int num = ThreadLocalRandom.current().nextInt(2000);
	map.put(null, num);
	final int result = map.remove(null);

	assertEquals(result, num);

    }

    @Test(dataProvider = "maps")
    public void removeEight(Map<String, Integer> map) {
	for (int index = 1; index < 10; index++) {
	    map.put(String.valueOf(index), index);
	}

	final int result = map.remove("9");

	assertEquals(result, 9);
	assertEquals(map.size(), 8);

    }

    @Test(dataProvider = "maps")
    public void removeNine(Map<Character, Integer> map) {
	for (int index = 0; index < 100; index += 16) {
	    map.put((char) index, index);
	}

	final int result = map.remove((char) 96);

	assertEquals(result, 96);

    }

}
