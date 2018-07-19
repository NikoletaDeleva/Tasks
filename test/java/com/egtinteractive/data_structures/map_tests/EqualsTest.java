package com.egtinteractive.data_structures.map_tests;

import static org.junit.Assert.assertFalse;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.data_structures.map.HashMap;
import com.egtinteractive.data_structures.map.Map;

public class EqualsTest {
    @DataProvider(name = "maps")
    public Object[][] createData() {
	return new Object[][] { { new HashMap<>() } };
    }

    @Test(dataProvider = "maps")
    public void equalsTest(final Map<Integer, String> map) {
	HashMap<Integer, String> hashMap = new HashMap<>();
	HashMap<Integer, String> newHashMap = new HashMap<>();

	for (int i = 0; i < 10; i++) {
	    map.put(i, "" + i + 1);
	    hashMap.put(i, "" + i + 1);
	    newHashMap.put(i, "" + i + 1);
	}

	assertTrue(map.equals(map));
	assertTrue(map.equals(hashMap));
	assertTrue(hashMap.equals(map));
	assertFalse(map.equals(null));
	assertEquals(newHashMap.equals(newHashMap), map.equals(newHashMap));

    }
}
