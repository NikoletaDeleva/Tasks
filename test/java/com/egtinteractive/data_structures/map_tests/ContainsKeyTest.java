package com.egtinteractive.data_structures.map_tests;

import static org.junit.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.data_structures.map.HashMap;
import com.egtinteractive.data_structures.map.Map;

public class ContainsKeyTest {
    @DataProvider(name = "maps")
    public Object[][] createData() {
	return new Object[][] { { new HashMap<>() } };
    }
    

    @Test(dataProvider = "maps")
    public void containsKeyTestShouldReturnTrue(final Map<Integer, String> map) {
	Integer randomKey = ThreadLocalRandom.current().nextInt(1, 20);
	map.put(randomKey, UUID.randomUUID().toString());
	Integer realKey = randomKey;
	assertTrue(map.containsKey(realKey));
    }
    
    @Test(dataProvider = "maps")
    public void otherContainsKeyTestShouldReturnTrue(final Map<Integer, String> map) {
	Integer otherKey = ThreadLocalRandom.current().nextInt(1, 20);
	assertFalse(map.containsKey(otherKey));
    }
    
}
