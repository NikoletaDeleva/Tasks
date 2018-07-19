package com.egtinteractive.data_structures.map_tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.data_structures.map.HashMap;
import com.egtinteractive.data_structures.map.Map;

public class ContainsValueTest {
    @DataProvider(name = "maps")
    public Object[][] createData() {
	return new Object[][] { { new HashMap<>() } };
    }

    @Test(dataProvider = "maps")
    public void containsValueTest(final Map<Integer, String> map) {
	String randomValue = UUID.randomUUID().toString();
	Integer randomKey = ThreadLocalRandom.current().nextInt();
	map.put(randomKey, randomValue);
	assertTrue(map.containsValue(randomValue));
    }
    
    @Test(dataProvider = "maps")
    public void otherContainsValueTestShouldReturnTrue(final Map<Integer, String> map) {
	String randValue = UUID.randomUUID().toString();
	assertFalse(map.containsValue(randValue));
    }
}
