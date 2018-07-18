package com.egtinteractive.data_structures.map_tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.data_structures.map.HashMap;
import com.egtinteractive.data_structures.map.Map;

public class GetTest extends TestMap {
    @DataProvider(name = "maps")
    public Object[][] createData() {
	return new Object[][] { { new HashMap<>() } };
    }

    @Test(dataProvider = "maps")
    public void getValueByKeyShouldReturnTrue(final Map<Integer, String> map) {

	final int randSize = ThreadLocalRandom.current().nextInt(1,100);

	fillMap(map, randSize);

	final String expectedValue = UUID.randomUUID().toString();
	final int randKey = ThreadLocalRandom.current().nextInt(1, randSize);

	map.put(randKey, expectedValue);

	final String realValue = map.get(randKey);

	assertEquals(expectedValue, realValue);
    }

    @Test(dataProvider = "maps")
    public void getNullKey(final Map<Integer, String> map) {
	final int randSize = ThreadLocalRandom.current().nextInt(1,100);

	fillMap(map, randSize);
	
	map.put(null, null);
	
	final String realValue = map.get(null);
	
	assertNull(realValue);
    }
    
}
