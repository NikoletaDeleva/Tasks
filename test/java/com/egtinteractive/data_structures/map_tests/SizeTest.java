package com.egtinteractive.data_structures.map_tests;

import static org.testng.Assert.assertEquals;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.data_structures.map.HashMap;
import com.egtinteractive.data_structures.map.Map;

public class SizeTest extends TestMap {
    @DataProvider(name = "maps")
    public Object[][] createData() {
	return new Object[][] { { new HashMap<>() } };
    }

    @Test(dataProvider = "maps")
    public void sizeTestShouldReturnTrue(final Map<Integer, String> map) {
	final int randSize = ThreadLocalRandom.current().nextInt(1,100);

	fillMap(map, randSize);

	final int realSize = map.size();

	assertEquals(randSize, realSize);
    }

    @Test(dataProvider = "maps")
    public void sizeTestAfterPutNullValueShouldReturnTrue(final Map<Integer, String> map) {
	final int randSize = ThreadLocalRandom.current().nextInt(1,100);

	fillMap(map, randSize);

	map.put(randSize + 1, null);

	final int realSize = map.size();
	final int expectedSize = randSize + 1;

	assertEquals(realSize, expectedSize);
    }

    @Test(dataProvider = "maps")
    public void sizeTestAfterPutNullKeyShouldReturnTrue(final Map<Integer, String> map) {
	final int randSize = ThreadLocalRandom.current().nextInt(1,100);

	fillMap(map, randSize);

	final String randValue = UUID.randomUUID().toString();

	map.put(null, randValue);

	final int realSize = map.size();
	final int expectedSize = randSize + 1;

	assertEquals(realSize, expectedSize);
    }
}
