package com.egtinteractive.data_structures.map_tests;

import static org.junit.Assert.assertFalse;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
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
    public void removeTest(final Map<Integer, String> map) {
	Integer key = ThreadLocalRandom.current().nextInt(1, 100);
	String value = UUID.randomUUID().toString();
	Integer keyNext = ThreadLocalRandom.current().nextInt(120, 200);
	String value1Next = UUID.randomUUID().toString();
	map.put(key, value);
	map.put(keyNext, value1Next);
	int size = map.size();
	map.remove(keyNext);
	assertEquals(map.size() + 1, size);
	assertFalse(map.containsKey(keyNext));
    }
    
    @Test(dataProvider = "maps")
    public void otherRemoveTest(final Map<Integer, String> map) {
	List<Integer> keys = new ArrayList<>();
	int count = 5000;
	for (Integer i = 0; i < count; i++) {
	    Integer key = i;
	    keys.add(key);
	    map.put(key, "" +i);
	}
	assertEquals(count, map.size());

	for (Integer key : keys) {
	    map.remove(key);
	    count--;
	    assertEquals(count, map.size());
	}

	
    }
}
