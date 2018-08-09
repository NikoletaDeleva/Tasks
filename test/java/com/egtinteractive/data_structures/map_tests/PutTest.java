package com.egtinteractive.data_structures.map_tests;

import static org.junit.Assert.assertNull;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.data_structures.map.HashMap;
import com.egtinteractive.data_structures.map.Map;

public class PutTest extends TestMap {
    @DataProvider(name = "maps")
    public Object[][] createData() {
	return new Object[][] { { new HashMap<>() } };
    }

    @Test(dataProvider = "maps")
    public void putKeyValueTest(final Map<UUID, UUID> map) {
	UUID randomKey = UUID.randomUUID();
	UUID randomValue = UUID.randomUUID();
	map.put(randomKey, UUID.randomUUID());
	UUID value = map.get(randomKey);
	map.put(randomKey, randomValue);
	assertNotEquals(randomValue, value);

    }

    @Test(dataProvider = "maps")
    public void putNullValueTest(final Map<UUID, UUID> map) {
	UUID randomKey = UUID.randomUUID();
	UUID nullValue = null;
	map.put(randomKey, nullValue);
	assertNull(nullValue);
    }

    @Test(dataProvider = "maps")
    public void putNullKeyTest(final Map<UUID, UUID> map) {
	UUID nullKey = null;
	UUID randomValue = UUID.randomUUID();
	map.put(nullKey, randomValue);
	assertNull(nullKey);
    }

    @Test(dataProvider = "maps")
    public void putTest(final Map<String, Integer> map) {
	final Integer firstValue = ThreadLocalRandom.current().nextInt(1, 100);
	final String first = String.valueOf(firstValue);

	
	for (int index = 0; index < 11130; index++) {
	    String str =String.valueOf(index);
	    
	    map.put(str, index);
	}
	map.put(first, firstValue);
	final Integer real = map.get(first);
	
	assertEquals(map.size(), 11130);
	assertEquals(real, firstValue);
    }
}
