package com.egtinteractive.data_structures.map_tests;

import static org.junit.Assert.assertFalse;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.UUID;

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
    public void equalsTest(final Map<String, String> map) {
	HashMap<String, String> hashMap = new HashMap<>();
	HashMap<String, String> newHashMap = new HashMap<>();

	for (int index = 0; index < 10; index++) {
	    final String randString = UUID.randomUUID().toString();
	    map.put(randString, "" + index + 1);
	    hashMap.put(randString, "" + index + 1);
	    newHashMap.put(randString, "" + index + 1);
	}

	assertTrue(map.equals(map));
	assertTrue(map.equals(hashMap));
	assertTrue(hashMap.equals(map));
	assertFalse(map.equals(null));
	assertEquals(newHashMap.equals(newHashMap), map.equals(newHashMap));

    }
}
